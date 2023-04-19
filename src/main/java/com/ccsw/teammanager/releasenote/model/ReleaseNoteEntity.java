package com.ccsw.teammanager.releasenote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "release_note")
public class ReleaseNoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "role", nullable = false)
  private String role;

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

  /**
   * @return the role
   */
  public String getRole() {

    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(String role) {

    this.role = role;
  }

}
