package com.ccsw.teammanager.releasenote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.teammanager.releasenote.model.ReleaseUserEntity;

public interface ReleaseUserRepository extends CrudRepository<ReleaseUserEntity, Long> {

  ReleaseUserEntity getByUsername(@Param("username") String username);

}
