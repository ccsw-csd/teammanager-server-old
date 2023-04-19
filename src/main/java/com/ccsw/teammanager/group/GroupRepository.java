package com.ccsw.teammanager.group;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccsw.teammanager.group.model.GroupEntity;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {

  @Query(value = "select * from `group` where name LIKE %:name% LIMIT 15", nativeQuery = true)
  public List<GroupEntity> filtrarGrupos(@Param("name") String name);
}
