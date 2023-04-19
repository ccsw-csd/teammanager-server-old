package com.ccsw.teammanager.center.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_center_with_festives")
public class ListadoCentrosFestivosEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "festive_actual_year")
  private Integer festiveActualYear;

  @Column(name = "festive_next_year")
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
