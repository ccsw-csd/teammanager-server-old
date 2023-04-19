package com.ccsw.teammanager.personabsence;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.personabsence.model.AbsenceSearchDto;
import com.ccsw.teammanager.personabsence.model.PersonAbsenceDto;

/**
 * @author aolmosca
 *
 */
@RequestMapping(value = "/personAbsence")
@RestController
public class PersonAbsenceController {

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  private PersonAbsenceService vAbsenceService;

  /**
   * Recupera la ausencias de una persona para un año
   *
   * @param year
   * @param username
   * @return
   */

  @RequestMapping(path = "/{year}/fromUser-groupByMonth/", method = RequestMethod.GET)
  public Map<Integer, List<PersonAbsenceDto>> getYearAndUsername(@PathVariable("year") Integer year) {

    String username = UserUtils.getUserDetails().getUsername();

    return this.vAbsenceService.findYearAndUsername(username, year);

  }

  /**
   * @param date
   */
  @RequestMapping(path = "/save/", method = RequestMethod.POST)
  public void save(@RequestBody AbsenceSearchDto dto) {

    String username = UserUtils.getUserDetails().getUsername();
    this.vAbsenceService.save(dto.getYear(), dto.getDtos(), dto.getDates(), username);
  }

}
