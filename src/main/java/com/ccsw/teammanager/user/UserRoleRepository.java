package com.ccsw.teammanager.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.user.model.UserRoleEntity;

/**
 * @author pajimene
 *
 */
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

    /**
     * Recupera los roles de un usuario con su username
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserRoleEntity> findByUser_Username(String username);

}
