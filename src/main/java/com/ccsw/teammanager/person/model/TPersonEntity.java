package com.ccsw.teammanager.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author aolmosca
 *
 */
@Entity
@Table(name = "t_person")
public class TPersonEntity {

  @Id
  @Column(name = "saga", nullable = false)
  private String saga;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "center")
  private String center;

  @Column(name = "grade")
  private String grade;

  @Column(name = "businesscode")
  private String businesscode;

  @Column(name = "pucode")
  private String pucode;

  @Column(name = "startdate")
  private String startDate;

  @Column(name = "jobrole")
  private String jobrole;

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
   * @return center
   */
  public String getCenter() {

    return this.center;
  }

  /**
   * @param center new value of {@link #getcenter}.
   */
  public void setCenter(String center) {

    this.center = center;
  }

  /**
   * @return grade
   */
  public String getGrade() {

    return this.grade;
  }

  /**
   * @param grade new value of {@link #getgrade}.
   */
  public void setGrade(String grade) {

    this.grade = grade;
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
   * @return pucode
   */
  public String getPucode() {

    return this.pucode;
  }

  /**
   * @param pucode new value of {@link #getpucode}.
   */
  public void setPucode(String pucode) {

    this.pucode = pucode;
  }

  /**
   * @return startDate
   */
  public String getStartDate() {

    return this.startDate;
  }

  /**
   * @param startDate new value of {@link #getstartDate}.
   */
  public void setStartDate(String startDate) {

    this.startDate = startDate;
  }

  /**
   * @return jobrole
   */
  public String getJobrole() {

    return this.jobrole;
  }

  /**
   * @param jobrole new value of {@link #getjobrole}.
   */
  public void setJobrole(String jobrole) {

    this.jobrole = jobrole;
  }

}
