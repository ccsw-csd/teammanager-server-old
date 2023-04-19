package com.ccsw.teammanager.personabsence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccsw.teammanager.personabsence.model.PersonAbsenceDto;

/**
 * @author aolmosca
 *
 */
public interface PersonAbsenceService {

  Map<Integer, List<PersonAbsenceDto>> findYearAndUsername(String username, Integer year);

  void save(Integer year, List<PersonAbsenceDto> dtos, List<Date> dates, String username);

}
