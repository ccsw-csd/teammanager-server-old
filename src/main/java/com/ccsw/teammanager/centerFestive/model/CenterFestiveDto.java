package com.ccsw.teammanager.centerFestive.model;

import java.sql.Date;

/**
 * TODO apastorm This type ...
 *
 */
public class CenterFestiveDto {
  public long id;

  public long centerid;

  public int year;

  public int month;

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
   * @return centerid
   */
  public long getCenterid() {

    return this.centerid;
  }

  /**
   * @param centerid new value of {@link #getcenterid}.
   */
  public void setCenterid(long centerid) {

    this.centerid = centerid;
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
