package com.ccsw.teammanager.person.model;

import java.io.Serializable;

/**
 * @author aolmosca
 *
 */
public class PersonInconsistencyEntityPK implements Serializable {
  private static final long serialVersionUID = 1L;

  private String saga;

  private String username;

  private String name;

  private String lastname;

  /**
   * The constructor.
   */
  public PersonInconsistencyEntityPK() {

  }

  /**
   * The constructor.
   *
   * @param saga
   * @param username
   * @param name
   * @param lastname
   */
  public PersonInconsistencyEntityPK(String saga, String username, String name, String lastname) {

    super();
    this.saga = saga;
    this.username = username;
    this.name = name;
    this.lastname = lastname;
  }

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
   * @return serialversionuid
   */
  public static long getSerialversionuid() {

    return serialVersionUID;
  }

}
