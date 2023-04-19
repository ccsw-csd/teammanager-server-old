package com.ccsw.teammanager.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author aolmosca
 *
 */
@Entity
@Table(name = "v_person_number_absences")
@IdClass(PersonInconsistencyEntityPK.class)
public class PersonInconsistencyEntity {

  @Id
  @Column(name = "saga", nullable = false)
  private String saga;

  @Id
  @Column(name = "username", nullable = false)
  private String username;

  @Id
  @Column(name = "name", nullable = false)
  private String name;

  @Id
  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "center_id")
  private Integer centerId;

  @Column(name = "businesscode")
  private String businesscode;

  @Column(name = "email")
  private String email;

  @Column(name = "number_absences", nullable = false)
  private Integer numberAbsences;

  /**
   * @return saga
   */
  public String getSaga() {

    return this.saga;
  }

  /**
   * @param saga new value of {@link #getsaga}.
   */
  public void setSaga(String saga) {

    this.saga = saga;
  }

  /**
   * @return username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return lastname
   */
  public String getLastname() {

    return this.lastname;
  }

  /**
   * @param lastname new value of {@link #getlastname}.
   */
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  /**
   * @return centerId
   */
  public Integer getCenterId() {

    return this.centerId;
  }

  /**
   * @param centerId new value of {@link #getcenterId}.
   */
  public void setCenterId(Integer centerId) {

    this.centerId = centerId;
  }

  /**
   * @return businesscode
   */
  public String getBusinesscode() {

    return this.businesscode;
  }

  /**
   * @param businesscode new value of {@link #getbusinesscode}.
   */
  public void setBusinesscode(String businesscode) {

    this.businesscode = businesscode;
  }

  /**
   * @return numberAbsences
   */
  public Integer getNumberAbsences() {

    return this.numberAbsences;
  }

  /**
   * @param numberAbsences new value of {@link #getnumberAbsences}.
   */
  public void setNumberAbsences(Integer numberAbsences) {

    this.numberAbsences = numberAbsences;
  }

  /**
   * @return email
   */
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getemail}.
   */
  public void setEmail(String email) {

    this.email = email;
  }

}
