package com.ccsw.teammanager.publico;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.teammanager.center.CenterService;
import com.ccsw.teammanager.center.model.CenterEntity;
import com.ccsw.teammanager.group.GroupService;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.personabsence.PersonAbsenceRepository;
import com.ccsw.teammanager.personabsence.model.PersonAbsenceExportDto;

@Service
public class PublicoServiceImpl implements PublicoService {

    @Autowired
    private CenterService centerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PersonAbsenceRepository personAbsenceRepository;

    @Override
    public List<PersonEntity> getPersons(String query) {

        return this.groupService.getPersons(query);
    }

    @Override
    public List<CenterEntity> getCenters() {

        return this.centerService.getAllCenters();

    }

    @Override
    public byte[] generatePersonAbsenceExport(Integer year) throws Exception {

        List<PersonAbsenceExportDto> data = personAbsenceRepository.findPersonAbsenceForExport(year);

        Path tempFile = Files.createTempFile(null, null);

        List<String> lines = new ArrayList<>(data.size() + 1);
        lines.add("globalEmployeeId,username,saga,email,active,year,month,day,type,absenceType,status");

        data.stream().forEach(item -> {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getGlobal_employee_id());
            sb.append(",");

            sb.append(item.getUsername());
            sb.append(",");

            sb.append(item.getSaga());
            sb.append(",");

            sb.append(item.getEmail());
            sb.append(",");

            sb.append(item.getActive());
            sb.append(",");

            sb.append(item.getYear());
            sb.append(",");

            sb.append(item.getMonth());
            sb.append(",");

            sb.append(item.getDate().getDate());
            sb.append(",");

            sb.append(item.getType());
            sb.append(",");

            sb.append(item.getAbsence_type());
            sb.append(",");

            sb.append(item.getStatus());

            lines.add(sb.toString());
        });

        Files.write(tempFile, lines);

        return Files.readAllBytes(tempFile);
    }

}
