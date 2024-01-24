package com.ccsw.teammanager.personabsence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.teammanager.personabsence.model.PersonAbsenceEntity;
import com.ccsw.teammanager.personabsence.model.PersonAbsenceExportDto;

/**
 * @author aolmosca
 *
 */
public interface PersonAbsenceRepository extends CrudRepository<PersonAbsenceEntity, String> {

    List<PersonAbsenceEntity> findByYearAndPersonUsername(Integer year, String username);

    List<PersonAbsenceEntity> findByPerson_IdInAndDateBetween(List<Integer> personIds, Date startDate, Date endDate);

    //@Query(name = "findPersonAbsenceForExport", nativeQuery = true)
    @Query(value = "select t.global_employee_id as global_employee_id, t.username as username, t.saga as saga, t.email as email, t.active as active, t.year as year, t.month as month, t.date as date, t.type as type, t.absence_type as absence_type, t.status as status " //
            + "from (" //
            + "    select p.username as username, p.saga as saga, p.global_employee_id as global_employee_id, p.email as email, p.active as active, f.date as date, f.year as year, f.month as month, 'F' as type, null as absence_type, null as status " //
            + "    from teammanager.person p join teammanager.festive f on p.center_id = f.center_id and f.year = :year and p.global_employee_id in (select global_employee_id from personal.all_person where pucode is null or pucode = \"\" and center in (\"MJV\", \"VL3\") and global_employee_id is not null) " //
            + "    union all " //
            + "    select p.username as username, p.saga as saga, p.global_employee_id as global_employee_id, p.email as email, p.active as active, a.date as date, a.year as year, a.month as month, 'P' as type, a.type as absence_type, a.status as status " //
            + "    from teammanager.person p join teammanager.absence_pon a on a.saga = p.saga and a.year = :year and p.global_employee_id in (select global_employee_id from personal.all_person where pucode is null or pucode = \"\" and center in (\"MJV\", \"VL3\") and global_employee_id is not null) " //
            + ") t", nativeQuery = true)
    public List<PersonAbsenceExportDto> findPersonAbsenceForExport(@Param("year") Integer year);

}
