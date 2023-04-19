package com.ccsw.teammanager.person;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ccsw.teammanager.person.model.PersonDto;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.person.model.PersonInconsistencyEntity;
import com.ccsw.teammanager.person.model.PersonViewDto;

/**
 * @author aolmosca
 *
 */
public interface PersonService {

  PersonEntity personExists(String username);

  boolean createOrUpdate(PersonDto person);

  void create(PersonDto personDto);

  void update(PersonDto personDto);

  public List<PersonViewDto> notInPerson();

  public Page<PersonEntity> personWithoutCenter(Pageable pageable);

  public Page<PersonEntity> personWithSagaOrUserDuplicated(Pageable pageable);

  public Page<PersonInconsistencyEntity> personInconsistencies(Pageable pageable, Integer center);

  /**
   * @param username
   * @return
   */
  PersonEntity getManager(String username);
}
