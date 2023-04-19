package com.ccsw.teammanager.center.model;

/**
 * TODO apastorm This type ...
 *
 */
public class ListadoCentrosFestivosDto {
  private Integer id;

  private String name;

  private Integer festiveActualYear;

  private Integer festiveNextYear;

  /**
   * @return id
   */
  public Integer getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(Integer id) {

    this.id = id;
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
   * @return festiveActualYear
   */
  public Integer getFestiveActualYear() {

    return this.festiveActualYear;
  }

  /**
   * @param festiveActualYear new value of {@link #getfestiveActualYear}.
   */
  public void setFestiveActualYear(Integer festiveActualYear) {

    this.festiveActualYear = festiveActualYear;
  }

  /**
   * @return festiveNextYear
   */
  public Integer getFestiveNextYear() {

    return this.festiveNextYear;
  }

  /**
   * @param festiveNextYear new value of {@link #getfestiveNextYear}.
   */
  public void setFestiveNextYear(Integer festiveNextYear) {

    this.festiveNextYear = festiveNextYear;
  }

}
