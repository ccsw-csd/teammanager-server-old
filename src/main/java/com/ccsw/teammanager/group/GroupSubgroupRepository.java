package com.ccsw.teammanager.group;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.teammanager.group.model.GroupEntity;
import com.ccsw.teammanager.group.model.GroupSubgroupEntity;

/**
 * TODO apastorm This type ...
 *
 */
public interface GroupSubgroupRepository extends CrudRepository<GroupSubgroupEntity, Long> {

  /**
   * @param id
   * @return
   */
  @Query(value = "select sg from GroupEntity sg where sg.id in (select gsub.subgroup_id from GroupSubgroupEntity gsub where gsub.group_id = :id)")
  public List<GroupEntity> filtrarSubgruposDelGrupo(@Param("id") long id);

  /**
   * @param id
   */
  @Modifying
  @Query(value = "delete from GroupSubgroupEntity where group_id = :id")
  void deleteAllById(Long id);

  /**
   * @param subgroup_id
   * @return
   */
  @Query("select count(sg) from GroupSubgroupEntity sg where subgroup_id = :sbgr")
  public int comprobarSubgrupo(@Param("sbgr") Long sbgr);
}
