package com.ccsw.teammanager.releasenote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.releasenote.model.ReleaseNoteEntity;
import com.ccsw.teammanager.releasenote.model.ReleaseUserEntity;

@Service
@Transactional
public class ReleaseNoteServiceImpl implements ReleaseNoteService {

    @Autowired
    ReleaseNoteRepository releaseNoteRepository;

    @Autowired
    ReleaseUserRepository releaseUserRepository;

    @Override
    @Transactional(readOnly = false)
    public List<ReleaseNoteEntity> findNewReleases() {

        String username = UserUtils.getUserDetails().getUsername();

        ReleaseUserEntity releaseUser = this.releaseUserRepository.getByUsername(username);
        Long lastRead = Long.MAX_VALUE;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        if (releaseUser != null) {
            lastRead = releaseUser.getLastReadId();
        } else {
            releaseUser = new ReleaseUserEntity();
            releaseUser.setUsername(username);
        }
        releaseUser.setLastConnection(dtf.format(LocalDateTime.now()));

        List<ReleaseNoteEntity> list = this.releaseNoteRepository.findByRoleAndLastRead(UserUtils.getMaximumRole(), lastRead);

        if (lastRead.equals(Long.MAX_VALUE) || (list != null && list.size() > 0)) {

            lastRead = this.releaseNoteRepository.getMaxId();
            releaseUser.setLastReadId(lastRead);
        }
        this.releaseUserRepository.save(releaseUser);

        return list;
    }

}
