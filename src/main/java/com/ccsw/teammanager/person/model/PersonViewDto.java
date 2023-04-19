package com.ccsw.teammanager.person.model;

/**
 * @author aolmosca
 *
 */
public class PersonViewDto {
  private String username;

  private String name;

  private String lastname;

  private String center;

  private String email;

  private Integer numberAbsences;

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

}
