package com.ccsw.teammanager.releasenote;

import java.util.List;

import com.ccsw.teammanager.releasenote.model.ReleaseNoteEntity;

public interface ReleaseNoteService {

  List<ReleaseNoteEntity> findNewReleases();

}
