package com.ccsw.teammanager.group;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.group.model.EditGroupDto;
import com.ccsw.teammanager.group.model.GroupDto;
import com.ccsw.teammanager.group.model.GroupEntity;
import com.ccsw.teammanager.group.model.GroupManagerEntity;
import com.ccsw.teammanager.group.model.GroupMemberEntity;
import com.ccsw.teammanager.group.model.GroupSubgroupEntity;
import com.ccsw.teammanager.group.model.PublicGroupEntity;
import com.ccsw.teammanager.group.model.RespuestaValidarBorradoDto;
import com.ccsw.teammanager.person.PersonRepository;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.user.UserRepository;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupSubgroupRepository groupSubgroupRepository;

    @Autowired
    GroupManagerRepository groupManagerRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
    PublicGroupRepository publicGroupRepository;

    @Override
    public List<PublicGroupEntity> findPublicGroupsFromConnectedUser() {

        String username = UserUtils.getUserDetails().getUsername();

        return publicGroupRepository.findByUsername(username);
    }

    @Override
    public List<GroupEntity> getSubgroups(String name) {

        name = name.replaceAll(" ", "%");
        return this.groupRepository.filtrarGrupos(name);
    }

    @Override
    public List<PersonEntity> getPersons(String name) {

        name = name.replaceAll(" ", "%");
        return this.personRepository.filtrarPersonasActivas(name);
    }

    @Override
    public GroupEntity save(GroupDto data) {

        if (StringUtils.hasText(data.getExternalId())) {
            return saveExternalGroup(data);
        }

        return saveLocalGroup(data);

    }

    @Override
    public EditGroupDto getGroup(long id) {

        EditGroupDto editGroup = new EditGroupDto();
        editGroup.setId(id);

        GroupEntity group = this.groupRepository.findById(id).get();

        editGroup.setName(group.getName());
        editGroup.setExternalId(group.getExternalId());
        editGroup.setPublicGroup(group.isPublicGroup());

        List<PersonEntity> managers = this.groupManagerRepository.filtrarManagersDelGrupo(id);
        List<PersonEntity> members = this.groupMemberRepository.filtrarMiembrosDelGrupo(id);
        List<GroupEntity> subgroups = this.groupSubgroupRepository.filtrarSubgruposDelGrupo(id);

        editGroup.setManagers(managers);
        editGroup.setMembers(members);
        editGroup.setSubgroups(subgroups);

        return editGroup;
    }

    @Override
    public RespuestaValidarBorradoDto validarUsuario(Long group_id) {

        RespuestaValidarBorradoDto response = new RespuestaValidarBorradoDto();
        response.setTitulo("Error de validacion.");
        PersonEntity userId = new PersonEntity();
        userId = this.personRepository.findIdByUsername(UserUtils.getUserDetails().getUsername());
        Long existe = this.groupManagerRepository.validarGestor(Long.valueOf(group_id), Long.valueOf(userId.getId()));

        if (UserUtils.hasRole("ADMIN")) {
            if (this.groupSubgroupRepository.comprobarSubgrupo(group_id) != 0) {
                response.setInformacion("El grupo tiene subgrupos asignados. No se puede borrar.");
                response.setActivo(true);
            } else {
                borrarGrupo(group_id);
                response.setActivo(false);
            }
        } else if (existe != 0) {
            if (this.groupSubgroupRepository.comprobarSubgrupo(group_id) != 0) {
                response.setInformacion("El grupo tiene subgrupos asignados. No se puede borrar.");
                response.setActivo(true);
            } else {
                borrarGrupo(group_id);
                response.setActivo(false);
            }
        } else {
            response.setInformacion("No eres ni gestor ni admin, no puedes borrar el grupo.");
            response.setActivo(true);
        }

        return response;
    }

    @Override
    public void borrarGrupo(Long subgroup_id) {

        this.groupManagerRepository.deleteAllById(subgroup_id);
        this.groupMemberRepository.deleteAllById(subgroup_id);
        this.groupSubgroupRepository.deleteAllById(subgroup_id);
        this.groupRepository.deleteById(subgroup_id);
    }

    private GroupEntity saveExternalGroup(GroupDto data) {

        GroupEntity group = this.groupRepository.findById(data.getId()).orElse(null);
        group.setPublicGroup(data.getPublicGroup());

        this.groupRepository.save(group);

        return group;

    }

    private GroupEntity saveLocalGroup(GroupDto data) {

        GroupEntity group = new GroupEntity();
        ArrayList<GroupManagerEntity> managersGuardados = new ArrayList<GroupManagerEntity>();
        ArrayList<GroupMemberEntity> membersGuardados = new ArrayList<GroupMemberEntity>();
        ArrayList<GroupSubgroupEntity> subgroupsGuardados = new ArrayList<GroupSubgroupEntity>();

        if (data.getId() != null) {
            group = this.groupRepository.findById(data.getId()).orElse(null);
            group.setName(data.getName());
            this.groupManagerRepository.deleteAllById(data.getId());
            this.groupMemberRepository.deleteAllById(data.getId());
            if (this.groupSubgroupRepository.findById(data.getId()) != null)
                this.groupSubgroupRepository.deleteAllById(data.getId());
        }

        BeanUtils.copyProperties(data, group);

        this.groupRepository.save(group);

        for (int i = 0; i < data.getManagers().size(); i++) {
            GroupManagerEntity groupManager = new GroupManagerEntity();
            groupManager.setPerson_id(data.getManagers().get(i).getId());
            groupManager.setGroup_id(group.getId());
            if (!managersGuardados.contains(groupManager))
                this.groupManagerRepository.save(groupManager);
            managersGuardados.add(groupManager);
        }
        if (data.getMembers() != null) {
            for (int i = 0; i < data.getMembers().size(); i++) {
                GroupMemberEntity groupMember = new GroupMemberEntity();
                groupMember.setMember_id(data.getMembers().get(i).getId());
                groupMember.setGroup_id(group.getId());
                if (!membersGuardados.contains(groupMember))
                    this.groupMemberRepository.save(groupMember);
                membersGuardados.add(groupMember);
            }
        }
        if (data.getSubgroups() != null) {
            for (int i = 0; i < data.getSubgroups().size(); i++) {
                GroupSubgroupEntity groupSubgroups = new GroupSubgroupEntity();
                groupSubgroups.setSubgroup_id(data.getSubgroups().get(i).getId());
                groupSubgroups.setGroup_id(group.getId());
                if (!subgroupsGuardados.contains(groupSubgroups))
                    this.groupSubgroupRepository.save(groupSubgroups);
                subgroupsGuardados.add(groupSubgroups);
            }
        }
        return group;
    }

}
