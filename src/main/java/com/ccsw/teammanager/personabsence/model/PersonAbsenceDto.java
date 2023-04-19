package com.ccsw.teammanager.personabsence.model;

import java.util.Date;

import com.ccsw.teammanager.person.model.PersonDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author aolmosca
 *
 */
public class PersonAbsenceDto {

  private String id;

  private PersonDto person;

  private Integer year;

  private Integer month;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
  private Date date;

  private String type;
  
  private String absence_type;

  /**
   * @return id
   */
  public String getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(String id) {

    this.id = id;
  }

  /**
   * @return person
   */
  public PersonDto getPerson() {

    return this.person;
  }

  /**
   * @param person new value of {@link #getperson}.
   */
  public void setPerson(PersonDto person) {

    this.person = person;
  }

  /**
   * @return year
   */
  public Integer getYear() {

    return this.year;
  }

  /**
   * @param year new value of {@link #getyear}.
   */
  public void setYear(Integer year) {

    this.year = year;
  }

  /**
   * @return month
   */
  public Integer getMonth() {

    return this.month;
  }

  /**
   * @param month new value of {@link #getmonth}.
   */
  public void setMonth(Integer month) {

    this.month = month;
  }

  /**
   * @return date
   */
  public Date getDate() {

    return this.date;
  }

  /**
   * @param date new value of {@link #getdate}.
   */
  public void setDate(Date date) {

    this.date = date;
  }

  /**
   * @return type
   */
  public String getType() {

    return this.type;
  }

  /**
   * @param type new value of {@link #gettype}.
   */
  public void setType(String type) {

    this.type = type;
  }

  public String getAbsence_type() {
	  
	  return this.absence_type;
  }
  
  public void setAbsence_type(String absence_type) {
	  
	  this.absence_type = absence_type;
  }

}
