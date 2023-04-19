package com.ccsw.teammanager.center;

import java.util.List;

import com.ccsw.teammanager.center.model.CenterEntity;
import com.ccsw.teammanager.center.model.ListadoCentrosFestivosEntity;

/**
 * @author aolmosca
 *
 */
public interface CenterService {
  List<CenterEntity> getAllCenters();

  CenterEntity getById(Integer id);

  /**
   * @return
   */
  List<ListadoCentrosFestivosEntity> listadoCentrosFestivos();
}
