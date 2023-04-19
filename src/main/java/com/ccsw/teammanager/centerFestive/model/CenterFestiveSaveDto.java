package com.ccsw.teammanager.centerFestive.model;

import java.sql.Date;
import java.util.List;

/**
 * TODO apastorm This type ...
 *
 */
public class CenterFestiveSaveDto {

  private int year;

  private List<Date> dates;

  private long centerid;

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
   * @return dates
   */
  public List<Date> getDates() {

    return this.dates;
  }

  /**
   * @param dates new value of {@link #getdates}.
   */
  public void setDates(List<Date> dates) {

    this.dates = dates;
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

}
