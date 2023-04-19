package com.ccsw.teammanager.group;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.group.model.PublicGroupEntity;

public interface PublicGroupRepository extends CrudRepository<PublicGroupEntity, String> {

  public List<PublicGroupEntity> findByUsername(String username);

}
