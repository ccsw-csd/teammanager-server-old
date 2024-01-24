package com.ccsw.teammanager.publico;

import java.util.List;

import com.ccsw.teammanager.center.model.CenterEntity;
import com.ccsw.teammanager.person.model.PersonEntity;

public interface PublicoService {

    List<CenterEntity> getCenters();

    List<PersonEntity> getPersons(String query);

    byte[] generatePersonAbsenceExport(Integer year) throws Exception;

}
