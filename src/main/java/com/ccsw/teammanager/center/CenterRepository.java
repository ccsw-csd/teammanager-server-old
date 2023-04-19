package com.ccsw.teammanager.center;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.teammanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
public interface CenterRepository extends CrudRepository<CenterEntity, Long> {
  CenterEntity findById(Integer id);
}
