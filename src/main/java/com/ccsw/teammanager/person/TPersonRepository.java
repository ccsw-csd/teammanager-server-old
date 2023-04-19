package com.ccsw.teammanager.person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.person.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
public interface TPersonRepository extends CrudRepository<TPersonEntity, Long> {
  List<TPersonEntity> findByUsernameNotIn(List<String> username);
}
