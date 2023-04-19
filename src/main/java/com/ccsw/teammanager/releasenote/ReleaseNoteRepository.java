package com.ccsw.teammanager.releasenote;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.releasenote.model.ReleaseNoteEntity;

public interface ReleaseNoteRepository extends CrudRepository<ReleaseNoteEntity, Long> {

  @Query(value = "select rn from ReleaseNoteEntity rn where ((rn.role is null) or (:role = 'GESTOR' and rn.role = 'GESTOR') or (:role = 'ADMIN')) and rn.id > :lastRead order by rn.id")
  List<ReleaseNoteEntity> findByRoleAndLastRead(String role, Long lastRead);

  @Query("select max(id) from ReleaseNoteEntity")
  Long getMaxId();

}
