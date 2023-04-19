package com.ccsw.teammanager.releasenote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.releasenote.model.ReleaseNoteDto;

@RequestMapping(value = "/releasenote")
@RestController
public class ReleaseNoteController {

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  private ReleaseNoteService releaseNoteService;

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public List<ReleaseNoteDto> find() {

    return beanMapper.mapList(this.releaseNoteService.findNewReleases(), ReleaseNoteDto.class);
  }

}
