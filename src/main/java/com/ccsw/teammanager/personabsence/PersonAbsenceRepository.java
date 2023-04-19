package com.ccsw.teammanager.personabsence;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.personabsence.model.PersonAbsenceEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonAbsenceRepository extends CrudRepository<PersonAbsenceEntity, String> {

  List<PersonAbsenceEntity> findByYearAndPersonUsername(Integer year, String username);

  List<PersonAbsenceEntity> findByPerson_IdInAndDateBetween(List<Integer> personIds, Date startDate, Date endDate);

}
