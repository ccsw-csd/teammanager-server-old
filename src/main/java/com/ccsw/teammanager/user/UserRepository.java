package com.ccsw.teammanager.user;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.user.model.UserEntity;

/**
 * @author pajimene
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  /**
   * Recupera un usuario con su username
   * @param username
   * @return
   * @throws Exception
   */
  UserEntity getByUsername(String username);

}
