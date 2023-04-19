package com.ccsw.teammanager.publico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.center.CenterService;
import com.ccsw.teammanager.center.model.CenterDto;
import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.group.GroupService;
import com.ccsw.teammanager.person.model.PersonDto;

@RequestMapping(value = "/api")
@RestController
public class PublicoController {

    @Autowired
    CenterService centerService;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GroupService groupService;

    /**
     * @param prefix
     * @return
     */
    @RequestMapping(path = "/person/{query}", method = RequestMethod.GET)
    public List<PersonDto> getPersons(@PathVariable(value = "query") String query) {

        return this.beanMapper.mapList(this.groupService.getPersons(query), PersonDto.class);
    }

    @RequestMapping(path = "/center", method = RequestMethod.GET)
    public List<CenterDto> getCenters() {

        return this.beanMapper.mapList(this.centerService.getAllCenters(), CenterDto.class);

    }

}
