package com.ccsw.teammanager.personabsence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.person.PersonRepository;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.personabsence.model.AbsenceEntity;
import com.ccsw.teammanager.personabsence.model.PersonAbsenceDto;

/**
 * @author aolmosca
 *
 */
@Service
@Transactional(readOnly = true)
public class PersonAbsenceServiceImpl implements PersonAbsenceService {

  @Autowired
  PersonAbsenceRepository vabsenceRepository;

  @Autowired
  PersonRepository personRepository;

  @Autowired
  AbsenceRepository absenceRepository;

  @Autowired
  private BeanMapper beanMapper;

  @Override
  public Map<Integer, List<PersonAbsenceDto>> findYearAndUsername(String username, Integer year) {

    HashMap<Integer, List<PersonAbsenceDto>> hashAbsence = new HashMap<>();

    List<PersonAbsenceDto> listAbsence = this.beanMapper
        .mapList(this.vabsenceRepository.findByYearAndPersonUsername(year, username), PersonAbsenceDto.class);

    for (PersonAbsenceDto vabsencedto : listAbsence) {
      hashAbsence.computeIfAbsent(vabsencedto.getMonth(), k -> new ArrayList<>()).add(vabsencedto);
    }

    return hashAbsence;
  }

  @Transactional(readOnly = false)
  @Override
  public void save(Integer year, List<PersonAbsenceDto> dtos, List<Date> dates, String username) {

    PersonEntity personEntity = this.personRepository.findByUsernameAndActiveTrue(username);
    this.absenceRepository.deleteBySagaAndYear(personEntity.getSaga(), year);

    for (int i = 0; i < dates.size(); i++) {
      AbsenceEntity newAbsenceEntity = new AbsenceEntity();
      
      Calendar calendar = dateToCalendar(dates.get(i));

      newAbsenceEntity.setDate(dates.get(i));

      newAbsenceEntity.setSaga(personEntity.getSaga());
      newAbsenceEntity.setMonth(calendar.get(Calendar.MONTH) + 1);
      newAbsenceEntity.setYear(calendar.get(Calendar.YEAR));
      //newAbsenceEntity.setType("A");
      newAbsenceEntity.setAbsence_type(dtos.get(i).getAbsence_type());
      
      this.absenceRepository.save(newAbsenceEntity);
    }
  }

  private Calendar dateToCalendar(Date date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }
}
