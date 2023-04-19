package com.ccsw.teammanager.personabsence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.teammanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
@Entity
@Table(name = "v_person_absence")
public class PersonAbsenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private String id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonEntity person;

  @Column(name = "year")
  private Integer year;

  @Column(name = "month")
  private Integer month;

  @Column(name = "date")
  private Date date;

  @Column(name = "type")
  private String type;
  
  @Column(name = "absence_type")
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
  public PersonEntity getPerson() {

    return this.person;
  }

  /**
   * @param person new value of {@link #getperson}.
   */
  public void setPerson(PersonEntity person) {

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
