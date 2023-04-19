package com.ccsw.teammanager.person;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccsw.teammanager.center.CenterService;
import com.ccsw.teammanager.person.model.PersonDto;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.person.model.PersonInconsistencyEntity;
import com.ccsw.teammanager.person.model.PersonViewDto;
import com.ccsw.teammanager.person.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  TPersonRepository tPersonRepository;

  @Autowired
  PersonInconsistencyRepository personInconsistencyRepository;

  @Autowired
  CenterService centerService;

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

  @Override
  public PersonEntity personExists(String username) {

    return this.personRepository.findByUsername(username);
  }

  @Override
  public boolean createOrUpdate(PersonDto personDto) {

    PersonEntity person = this.personRepository.findByUsername(personDto.getUsername());
    if (person == null || person.getId() == null) {
      if (personNotExists(personDto.getUsername(), personDto.getSaga())) {
        create(personDto);
        return true;
      }
      return sagaNotExists(personDto.getSaga());
    } else {
      personDto.setId(person.getId());
      update(personDto);
      return true;
    }
  }

  @Override
  public void create(PersonDto personDto) {

    PersonEntity personEntity = new PersonEntity();
    BeanUtils.copyProperties(personDto, personEntity, "id");
    personEntity.setActive(true);
    if (personEntity.getSaga() == null)
      personEntity.setSaga(personEntity.getUsername());

    this.personRepository.save(personEntity);

  }

  @Override
  public void update(PersonDto personDto) {

    PersonEntity personEntity = this.personRepository.findById(personDto.getId());
    BeanUtils.copyProperties(personDto, personEntity);
    this.personRepository.save(personEntity);

  }

  @Override
  public List<PersonViewDto> notInPerson() {

    List<String> usernames = new ArrayList();

    List<PersonEntity> personList = (List<PersonEntity>) this.personRepository.findAll();
    for (PersonEntity person : personList) {
      usernames.add(person.getUsername());
    }
    List<TPersonEntity> tPersonList = this.tPersonRepository.findByUsernameNotIn(usernames);
    List<PersonViewDto> personViewList = new ArrayList();
    for (TPersonEntity person : tPersonList) {
      PersonViewDto personView = new PersonViewDto();
      BeanUtils.copyProperties(person, personView);
      personViewList.add(personView);
    }

    return personViewList;
  }

  @Override
  public Page<PersonEntity> personWithoutCenter(Pageable pageable) {

    Page<PersonEntity> personList = this.personRepository.findByCenterIdIsNullAndActive(true, pageable);

    return personList;
  }

  @Override
  public Page<PersonInconsistencyEntity> personInconsistencies(Pageable pageable, Integer center) {

    Page<PersonInconsistencyEntity> personList = this.personInconsistencyRepository
        .findByNumberAbsencesLessThanAndCenterId(19, center, pageable);

    return personList;
  }

  @Override
  public Page<PersonEntity> personWithSagaOrUserDuplicated(Pageable pageable) {

    Page<PersonEntity> personList = this.personRepository.sagaOrusernameDuplicated(pageable);

    return personList;
  }

  private boolean personNotExists(String username, String saga) {

    if (this.personRepository.findByUsernameOrSaga(username, saga) == null)
      return true;
    else
      return false;
  }

  private boolean sagaNotExists(String saga) {

    if (this.personRepository.findBySaga(saga) == null)
      return true;
    else
      return false;
  }

  @Override
  public PersonEntity getManager(String username) {

    return this.personRepository.findByUsername(username);
  }
}
