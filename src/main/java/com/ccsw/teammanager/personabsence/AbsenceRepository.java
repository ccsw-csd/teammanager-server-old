package com.ccsw.teammanager.personabsence;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.personabsence.model.AbsenceEntity;

/**
 * @author aolmosca
 *
 */
public interface AbsenceRepository extends CrudRepository<AbsenceEntity, Integer> {

  AbsenceEntity findByDateAndSaga(Date date, String saga);

  Long deleteBySagaAndYear(String saga, Integer year);

}
