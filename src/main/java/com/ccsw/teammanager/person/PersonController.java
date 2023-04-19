package com.ccsw.teammanager.person;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.person.model.PersonDto;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.person.model.PersonInconsistencyDto;
import com.ccsw.teammanager.person.model.PersonInconsistencySearchDto;
import com.ccsw.teammanager.person.model.PersonViewDto;

/**
 * @author aolmosca
 *
 */
@RequestMapping(value = "/person")
@RestController
public class PersonController {

  @Autowired
  PersonService personService;

  @Autowired
  private BeanMapper beanMapper;

  @RequestMapping(path = "/{username}", method = RequestMethod.GET)
  public PersonDto get(@PathVariable("username") String username) {

    PersonDto person = new PersonDto();
    PersonEntity personEntity = this.personService.personExists(username);

    if (personEntity != null)
      BeanUtils.copyProperties(personEntity, person);

    return person;
  }

  @RequestMapping(path = "/", method = RequestMethod.PUT)
  public boolean create(@RequestBody PersonDto dto) {

    return this.personService.createOrUpdate(dto);

  }

  @RequestMapping(path = "/inconsistencies/", method = RequestMethod.POST)
  public Page<PersonInconsistencyDto> getInconsistencies(@RequestBody PersonInconsistencySearchDto dto) {

    return this.beanMapper.mapPage(this.personService.personInconsistencies(dto.getPageable(), dto.getCenter()),
        PersonInconsistencyDto.class);

  }

  @RequestMapping(path = "/inconsistencies/duplicated", method = RequestMethod.POST)
  public Page<PersonDto> getInconsistenciesDuplicated(@RequestBody PersonInconsistencySearchDto dto) {

    return this.beanMapper.mapPage(this.personService.personWithSagaOrUserDuplicated(dto.getPageable()),
        PersonDto.class);

  }

  @RequestMapping(path = "/inconsistencies/center", method = RequestMethod.POST)
  public Page<PersonDto> getInconsistenciesCenter(@RequestBody PersonInconsistencySearchDto dto) {

    return this.beanMapper.mapPage(this.personService.personWithoutCenter(dto.getPageable()), PersonDto.class);

  }

  @RequestMapping(path = "/inconsistencies/notInPerson", method = RequestMethod.POST)
  public List<PersonViewDto> getInconsistenciesNotInPerson() {

    return this.personService.notInPerson();

  }

}
