package com.ccsw.teammanager.releasenote.model;

public class ReleaseNoteDto {

  private Long id;

  private String version;

  private String text;

  /**
   * @return the id
   */
  public Long getId() {

    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return the version
   */
  public String getVersion() {

    return version;
  }

  /**
   * @param version the version to set
   */
  public void setVersion(String version) {

    this.version = version;
  }

  /**
   * @return the text
   */
  public String getText() {

    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {

    this.text = text;
  }

}
