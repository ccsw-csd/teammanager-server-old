package com.ccsw.teammanager.publico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.teammanager.center.model.CenterDto;
import com.ccsw.teammanager.config.exception.ForbiddenException;
import com.ccsw.teammanager.config.mapper.BeanMapper;
import com.ccsw.teammanager.person.model.PersonDto;

@RequestMapping(value = "/api")
@RestController
public class PublicoController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private PublicoService publicoService;

    @Value("${app.authorization.api.export}")
    private String authorizationExport;

    /**
     * @param prefix
     * @return
     */
    @RequestMapping(path = "/person/{query}", method = RequestMethod.GET)
    public List<PersonDto> getPersons(@PathVariable(value = "query") String query) {

        return this.beanMapper.mapList(this.publicoService.getPersons(query), PersonDto.class);
    }

    @RequestMapping(path = "/center", method = RequestMethod.GET)
    public List<CenterDto> getCenters() {

        return this.beanMapper.mapList(this.publicoService.getCenters(), CenterDto.class);

    }

    @RequestMapping(path = "/absence/{year}/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] generatePersonAbsenceExport(@PathVariable(value = "year", required = false) Integer year, @RequestHeader(value = "Authorization", required = false) String authorization) throws ForbiddenException {

        if (authorization == null || authorizationExport.equals(authorization) == false)
            throw new ForbiddenException();

        if (year == null)
            year = LocalDate.now().getYear();

        try {
            return publicoService.generatePersonAbsenceExport(year);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
