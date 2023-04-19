
package com.ccsw.teammanager.centerFestive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.centerFestive.model.CenterFestiveDto;
import com.ccsw.teammanager.centerFestive.model.CenterFestiveSaveDto;
import com.ccsw.teammanager.config.mapper.BeanMapper;

@RequestMapping(value = "/festives")
@RestController
public class CenterFestiveController {

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  CenterFestiveService centerFestiveService;

  /**
   * @param centerId
   * @param year
   * @return
   */
  @RequestMapping(path = "/{centerId}/{year}", method = RequestMethod.GET)
  public List<CenterFestiveDto> find(@PathVariable("centerId") long centerId, @PathVariable("year") int year) {

    return this.beanMapper.mapList(this.centerFestiveService.find(centerId, year), CenterFestiveDto.class);
  }

  /**
   * @param dtoSave
   */
  @RequestMapping(path = "/save/", method = RequestMethod.POST)
  public void save(@RequestBody CenterFestiveSaveDto dtoSave) {

    this.centerFestiveService.save(dtoSave);

  }
}
