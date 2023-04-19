package com.ccsw.teammanager.group.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO apastorm This type ...
 *
 */
@Entity
@Table(name = "`group`")
public class GroupEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "external_id")
  private String externalId;

  @Column(name = "public")
  private boolean publicGroup;

  /**
   * @return the publicGroup
   */
  public boolean isPublicGroup() {

    return publicGroup;
  }

  /**
   * @param publicGroup the publicGroup to set
   */
  public void setPublicGroup(boolean publicGroup) {

    this.publicGroup = publicGroup;
  }

  /**
   * @return id
   */
  public Long getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;

  }

  /**
   * @return externalId
   */
  public String getExternalId() {

    return this.externalId;
  }

  /**
   * @param externalId new value of {@link #getexternalId}.
   */
  public void setExternalId(String externalId) {

    this.externalId = externalId;
  }

}