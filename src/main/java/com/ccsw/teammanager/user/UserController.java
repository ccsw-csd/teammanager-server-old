package com.ccsw.teammanager.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.config.security.UserInfoDto;
import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.group.GroupService;
import com.ccsw.teammanager.group.model.PublicGroupEntity;
import com.ccsw.teammanager.person.PersonService;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.user.model.UserDto;

/**
 * @author pajimene
 *
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PersonService personService;

    @Autowired
    private BeanMapper beanMapper;

    /**
     * Recupera el usuario logado
     *
     * @return
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public UserDto get() {

        UserInfoDto userDetails = UserUtils.getUserDetails();
        UserDto dto = this.beanMapper.map(userDetails, UserDto.class);
        dto.setWithPerson(false);
        dto.setRole(UserUtils.getMaximumRole());

        List<PublicGroupEntity> publicGroups = this.groupService.findPublicGroupsFromConnectedUser();
        dto.setWithPublicGroups(publicGroups != null && publicGroups.size() > 0);

        PersonEntity person = this.personService.personExists(userDetails.getUsername());
        if (person != null)
            dto.setWithPerson(true);

        return dto;

    }

}
