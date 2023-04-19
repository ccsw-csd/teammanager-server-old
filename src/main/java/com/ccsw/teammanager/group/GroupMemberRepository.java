package com.ccsw.teammanager.group;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.teammanager.group.model.GroupMemberEntity;
import com.ccsw.teammanager.person.model.PersonEntity;

/**
 * TODO apastorm This type ...
 *
 */
public interface GroupMemberRepository extends CrudRepository<GroupMemberEntity, Long> {
  /**
   * @param id
   * @return
   */
  @Query(value = "select p from PersonEntity p where id in (select gmem.person_id from GroupMemberEntity gmem where gmem.group_id = :id)")
  public List<PersonEntity> filtrarMiembrosDelGrupo(@Param("id") long id);

  /**
   * @param id
   */
  @Modifying
  @Query(value = "delete from GroupMemberEntity where group_id = :id")
  void deleteAllById(Long id);
}
