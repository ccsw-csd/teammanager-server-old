package com.ccsw.teammanager.centerFestive.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "festive")
public class CenterFestiveEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  public long id;

  @Column(name = "center_id")
  public long centerId;

  @Column(name = "year")
  public int year;

  @Column(name = "month")
  public int month;

  @Column(name = "date")
  public Date date;

  /**
   * @return id
   */
  public long getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(long id) {

    this.id = id;
  }

  /**
   * @return centerId
   */
  public long getCenterId() {

    return this.centerId;
  }

  /**
   * @param centerId new value of {@link #getcenterId}.
   */
  public void setCenterId(long centerId) {

    this.centerId = centerId;
  }

  /**
   * @return year
   */
  public int getYear() {

    return this.year;
  }

  /**
   * @param year new value of {@link #getyear}.
   */
  public void setYear(int year) {

    this.year = year;
  }

  /**
   * @return month
   */
  public int getMonth() {

    return this.month;
  }

  /**
   * @param month new value of {@link #getmonth}.
   */
  public void setMonth(int month) {

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

}
