package com.ccsw.teammanager.forecast.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author aolmosca
 *
 */
public class ForecastExportDto {
  private Long groupId;

  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
  private Date init;

  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
  private Date end;

  private int type;

  /**
   * @return groupId
   */
  public Long getGroupId() {

    return this.groupId;
  }

  /**
   * @param groupId new value of {@link #getgroupId}.
   */
  public void setGroupId(Long groupId) {

    this.groupId = groupId;
  }

  /**
   * @return init
   */
  public Date getInit() {

    return this.init;
  }

  /**
   * @param init new value of {@link #getinit}.
   */
  public void setInit(Date init) {

    this.init = init;
  }

  /**
   * @return end
   */
  public Date getEnd() {

    return this.end;
  }

  /**
   * @param end new value of {@link #getend}.
   */
  public void setEnd(Date end) {

    this.end = end;
  }

  /**
   * @return type
   */
  public int isType() {

    return this.type;
  }

  /**
   * @param type new value of {@link #gettype}.
   */
  public void setType(int type) {

    this.type = type;
  }

}
