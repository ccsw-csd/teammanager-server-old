package com.ccsw.teammanager.personabsence.model;

import java.util.Date;
import java.util.List;

/**
 * @author aolmosca
 *
 */
public class AbsenceSearchDto {

  private Integer year;
  
  private List<Date> dates;
  
  private List<PersonAbsenceDto> dtos;

  /**
   * @return dates
   */
  public List<Date> getDates() {

    return this.dates;
  }

  /**
   * @param dates new value of {@link #getdates}.
   */
  public void setDates(List<Date> dates) {

    this.dates = dates;
  }
  
  /**
   * @return year
   */
  public Integer getYear() {

    return year;
  }

  /**
   * @param year new value of {@link #getyear}.
   */
  public void setYear(Integer year) {

    this.year = year;
  }
  
  public List<PersonAbsenceDto> getDtos() {

	  return this.dtos;
  }

  public void setDtos(List<PersonAbsenceDto> dtos) {

	  this.dtos = dtos;
  }

}
