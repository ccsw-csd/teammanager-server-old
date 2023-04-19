package com.ccsw.teammanager.person.model;

import org.springframework.data.domain.Pageable;

/**
 * @author aolmosca
 *
 */
public class PersonInconsistencySearchDto {
  private Pageable pageable;

  private String name;

  private Integer center;

  private Integer number_absences;

  /**
   * @return pageable
   */
  public Pageable getPageable() {

    return this.pageable;
  }

  /**
   * @param pageable new value of {@link #getpageable}.
   */
  public void setPageable(Pageable pageable) {

    this.pageable = pageable;
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
   * @return center
   */
  public Integer getCenter() {

    return this.center;
  }

  /**
   * @param center new value of {@link #getcenter}.
   */
  public void setCenter(Integer center) {

    this.center = center;
  }

  /**
   * @return number_absences
   */
  public Integer getNumber_absences() {

    return this.number_absences;
  }

  /**
   * @param number_absences new value of {@link #getnumber_absences}.
   */
  public void setNumber_absences(Integer number_absences) {

    this.number_absences = number_absences;
  }

}
