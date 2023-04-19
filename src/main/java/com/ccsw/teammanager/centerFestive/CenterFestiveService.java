package com.ccsw.teammanager.centerFestive;

import java.util.List;

import com.ccsw.teammanager.centerFestive.model.CenterFestiveEntity;
import com.ccsw.teammanager.centerFestive.model.CenterFestiveSaveDto;

/**
 * TODO apastorm This type ...
 *
 */
public interface CenterFestiveService {

  /**
   * @param centerid
   * @param year
   * @return
   */
  List<CenterFestiveEntity> find(long centerid, int year);

  /**
   * @param centerid
   * @param year
   * @param dates
   */
  void save(CenterFestiveSaveDto dto);
}
