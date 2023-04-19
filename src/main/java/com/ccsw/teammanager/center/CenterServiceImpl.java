package com.ccsw.teammanager.center;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.teammanager.center.model.CenterEntity;
import com.ccsw.teammanager.center.model.ListadoCentrosFestivosEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class CenterServiceImpl implements CenterService {

  @Autowired
  CenterRepository centerRepository;

  @Autowired
  ListadoCentrosFestivosRepository listadoCentrosFestivosRepository;

  private static final Logger LOG = LoggerFactory.getLogger(CenterServiceImpl.class);

  @Override
  public List<CenterEntity> getAllCenters() {

    Iterable<CenterEntity> centersEntities = this.centerRepository.findAll();
    List<CenterEntity> centerEntityList = new ArrayList<CenterEntity>();
    centersEntities.forEach(centerEntityList::add);

    return centerEntityList;
  }

  @Override
  public CenterEntity getById(Integer id) {

    return this.centerRepository.findById(id);
  }

  @Override
  public List<ListadoCentrosFestivosEntity> listadoCentrosFestivos() {

    return this.listadoCentrosFestivosRepository.findAllByOrderByNameAsc();

  }
}
