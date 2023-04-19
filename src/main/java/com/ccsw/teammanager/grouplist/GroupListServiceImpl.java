package com.ccsw.teammanager.grouplist;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.grouplist.model.GroupListEntity;
import com.ccsw.teammanager.grouplist.model.GroupListSearchDto;

/**
 *
 *
 */
@Service
@Transactional
public class GroupListServiceImpl implements GroupListService {

    @Autowired
    GroupListRepository groupListRepository;

    @Override
    public List<GroupListEntity> findAll(boolean adminView) {
        if (userIsAdmin() && adminView) {
            return this.groupListRepository.findAll();
        }

        return this.groupListRepository.findManagedGroups(UserUtils.getUserDetails().getUsername());
    }

    @Override
    public Page<GroupListEntity> findPage(GroupListSearchDto dto) {

        if (userIsAdmin() && dto.getViewAdmin()) {
            return this.groupListRepository.findAll(dto.getPageable());
        }

        if (userIsGestor()) {
            return this.groupListRepository.filtrarGestor(dto.getPageable(), UserUtils.getUserDetails().getUsername());
        }

        return this.groupListRepository.filtrarGruposPublicos(dto.getPageable(), UserUtils.getUserDetails().getUsername());
    }

    @Override
    public Page<GroupListEntity> findPageManager(GroupListSearchDto dto) {

        if (userIsAdmin() && dto.getViewAdmin()) {
            return this.groupListRepository.findAll(dto.getPageable());
        }

        return this.groupListRepository.filtrarManager(dto.getPageable(), UserUtils.getUserDetails().getUsername());
    }

    /**
     * @return
     */
    private boolean userIsGestor() {

        return userIsAdmin() || UserUtils.hasRole("GESTOR");
    }

    /**
     * @return
     */
    private boolean userIsAdmin() {

        return UserUtils.hasRole("ADMIN");
    }

}
