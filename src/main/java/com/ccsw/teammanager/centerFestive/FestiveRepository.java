package com.ccsw.teammanager.centerFestive;

import com.ccsw.teammanager.centerFestive.model.CenterFestiveEntity;

/**
 * TODO apastorm This type ...
 *
 */
public interface FestiveRepository {

  @SuppressWarnings("javadoc")
  CenterFestiveEntity findAllByCenteridAndYear(long centerid, int year);
}
