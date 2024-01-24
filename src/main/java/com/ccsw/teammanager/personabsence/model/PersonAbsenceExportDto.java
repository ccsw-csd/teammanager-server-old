package com.ccsw.teammanager.personabsence.model;

import java.util.Date;

public interface PersonAbsenceExportDto {

    String getGlobal_employee_id();

    String getUsername();

    String getSaga();

    String getEmail();

    Integer getActive();

    Integer getYear();

    Integer getMonth();

    Date getDate();

    String getType();

    String getAbsence_type();

    String getStatus();

}
