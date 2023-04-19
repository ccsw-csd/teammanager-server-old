package com.ccsw.teammanager.centerFestive;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.teammanager.center.CenterRepository;
import com.ccsw.teammanager.centerFestive.model.CenterFestiveEntity;
import com.ccsw.teammanager.centerFestive.model.CenterFestiveSaveDto;
import com.ccsw.teammanager.centerFestive.model.FestiveAuditEntity;
import com.ccsw.teammanager.config.security.UserUtils;

/**
 *
 */
@Service
public class CenterFestiveServiceImpl implements CenterFestiveService {

  @Autowired
  CenterFestiveRepository centerFestiveRepository;

  @Autowired
  FestiveAuditRepository festiveAuditRepository;

  @Autowired
  CenterRepository centerRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CenterFestiveEntity> find(long centerId, int year) {

    return this.centerFestiveRepository.findAllByCenterIdAndYear(centerId, year);

  }

  private void saveLog(CenterFestiveSaveDto dto) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void save(CenterFestiveSaveDto dto) {

    int year = dto.getYear();
    long centerId = dto.getCenterid();
    List<Date> dates = dto.getDates();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String user = UserUtils.getUserDetails().getUsername();
    String log = "";

    this.centerFestiveRepository.deleteAllByCenterIdAndYear(centerId, year);

    for (int i = 0; i < dates.size(); i++) {
      CenterFestiveEntity festivo = new CenterFestiveEntity();
      festivo.setCenterId(centerId);
      festivo.setDate(dates.get(i));
      festivo.setYear(year);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dates.get(i));
      festivo.setMonth(calendar.get(Calendar.MONTH) + 1);
      log = log + festivo.getDate().getDay() + "/" + festivo.getMonth() + ",";
      this.centerFestiveRepository.save(festivo);
    }
    log = log.substring(0, log.length() - 1);
    FestiveAuditEntity audit = new FestiveAuditEntity();
    audit.setCenterId(centerId);
    audit.setDate(timestamp);
    audit.setLog(log);
    audit.setMonth(timestamp.getMonth());
    audit.setYear(year);
    audit.setUsername(user);
    this.festiveAuditRepository.save(audit);

  }
}
