package com.ccsw.teammanager.utils;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author pajimene
 *
 */
@Service
public class UtilsServiceImpl implements UtilsService {

  private static final Logger LOG = LoggerFactory.getLogger(UtilsServiceImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public String getVersion() {

    try {
      return new Manifest(Application.class.getResourceAsStream("/META-INF/MANIFEST.MF")).getMainAttributes().get(Attributes.Name.IMPLEMENTATION_VERSION)
          .toString();
    } catch (Exception e) {
      LOG.error("Error al extraer la version", e);
    }

    return "?";
  }

}
