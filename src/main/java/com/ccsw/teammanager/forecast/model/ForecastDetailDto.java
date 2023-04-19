package com.ccsw.teammanager.forecast.model;

import java.util.List;

/**
 * @author aolmosca
 *
 */
public class ForecastDetailDto {
  List<ForecastDto> absences;

  boolean visible;

  /**
   * @return absences
   */
  public List<ForecastDto> getAbsences() {

    return this.absences;
  }

  /**
   * @param absences new value of {@link #getabsences}.
   */
  public void setAbsences(List<ForecastDto> absences) {

    this.absences = absences;
  }

  /**
   * @return visible
   */
  public boolean isVisible() {

    return this.visible;
  }

  /**
   * @param visible new value of {@link #getvisible}.
   */
  public void setVisible(boolean visible) {

    this.visible = visible;
  }

}
