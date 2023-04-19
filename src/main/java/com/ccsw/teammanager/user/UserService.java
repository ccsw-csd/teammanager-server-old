package com.ccsw.teammanager.user;

import java.util.List;

import com.ccsw.teammanager.user.model.UserEntity;
import com.ccsw.teammanager.user.model.UserRoleEntity;

/**
 * @author pajimene
 *
 */
public interface UserService {

    /**
     * Recupera un usuario con su username
     * @param username
     * @return
     * @throws Exception
     */
    UserEntity getByUsername(String username);

    List<UserRoleEntity> getRolesByUsername(String username);
}
