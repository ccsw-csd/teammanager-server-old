package com.ccsw.teammanager.grouplist.model;

import org.springframework.data.domain.Pageable;

/**
 * TODO apastorm This type ...
 *
 */
public class GroupListSearchDto {

  private Boolean viewAdmin = false;

  private Pageable pageable;

  /**
   * @return pageable
   */
  public Pageable getPageable() {

    return this.pageable;
  }

  /**
   * @param pageable new value of {@link #getpageable}.
   */
  public void setPageable(Pageable pageable) {

    this.pageable = pageable;
  }

  /**
   * @return viewAdmin
   */
  public Boolean getViewAdmin() {

    return viewAdmin;
  }

  /**
   * @param viewAdmin new value of {@link #getviewAdmin}.
   */
  public void setViewAdmin(Boolean viewAdmin) {

    this.viewAdmin = viewAdmin;
  }

}
