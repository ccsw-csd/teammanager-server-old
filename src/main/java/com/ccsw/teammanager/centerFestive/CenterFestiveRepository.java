package com.ccsw.teammanager.centerFestive;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.centerFestive.model.CenterFestiveEntity;

/**
 *
 */
public interface CenterFestiveRepository extends CrudRepository<CenterFestiveEntity, Long> {

  /**
   * @param centerid
   * @param year
   * @return
   */

  List<CenterFestiveEntity> findAllByCenterIdAndYear(long centerid, int year);

  /**
   * @param centerId
   * @param year
   */
  @Transactional
  void deleteAllByCenterIdAndYear(long centerId, int year);

}
