package com.ccsw.teammanager.person;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.teammanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

  PersonEntity findByUsername(String username);

  PersonEntity findByUsernameOrSaga(String username, String saga);

  PersonEntity findBySaga(String saga);

  PersonEntity findById(Integer id);

  PersonEntity findIdByUsername(String username);

  PersonEntity findByUsernameAndActiveTrue(String username);

  List<PersonEntity> findByUsernameNotIn(List<String> username);

  Page<PersonEntity> findByCenterIdIsNullAndActive(Boolean active, Pageable pageable);

  @Query(value = "select * from person p2 where saga in (select sub.saga from (select p.saga, count(p.saga) from person p GROUP BY p.saga HAVING COUNT(p.saga)>1)sub) or username in (select sub.username from (select p.username, count(p.username) from person p GROUP BY p.username HAVING COUNT(p.username)>1)sub)", nativeQuery = true)
  public Page<PersonEntity> sagaOrusernameDuplicated(Pageable pageable);

  @Query(value = "select * from person where concat(name, ' ', lastname, ' ', username) LIKE %:name% LIMIT 15", nativeQuery = true)
  public List<PersonEntity> filtrarPersonas(@Param("name") String name);

  @Query(value = "select * from person where id in (select person_id from V_GROUP_MEMBERS_ALL where group_id = :groupId)", nativeQuery = true)
  public List<PersonEntity> findPersonByGroupId(@Param("groupId") Long groupId);

  @Query(value = "select * from person where concat(name, ' ', lastname, ' ', username) LIKE %:name% AND active = TRUE LIMIT 15", nativeQuery = true)
  public List<PersonEntity> filtrarPersonasActivas(String name);

}
