package com.ccsw.teammanager.group.model;

import java.util.List;

import com.ccsw.teammanager.person.model.PersonEntity;

/**
 * TODO apastorm This type ...
 *
 */
public class EditGroupDto {
  private Long id;

  private String name;

  private boolean publicGroup;

  private String externalId;

  private List<PersonEntity> managers;

  private List<PersonEntity> members;

  private List<GroupEntity> subgroups;

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
   * @return managers
   */
  public List<PersonEntity> getManagers() {

    return this.managers;
  }

  /**
   * @param managers new value of {@link #getmanagers}.
   */
  public void setManagers(List<PersonEntity> managers) {

    this.managers = managers;
  }

  /**
   * @return members
   */
  public List<PersonEntity> getMembers() {

    return this.members;
  }

  /**
   * @param members new value of {@link #getmembers}.
   */
  public void setMembers(List<PersonEntity> members) {

    this.members = members;
  }

  /**
   * @return subgroups
   */
  public List<GroupEntity> getSubgroups() {

    return this.subgroups;
  }

  /**
   * @param subgroups new value of {@link #getsubgroups}.
   */
  public void setSubgroups(List<GroupEntity> subgroups) {

    this.subgroups = subgroups;
  }

  /**
   * @return the externalId
   */
  public String getExternalId() {

    return externalId;
  }

  /**
   * @param externalId the externalId to set
   */
  public void setExternalId(String externalId) {

    this.externalId = externalId;
  }

  /**
   * @return the publicGroup
   */
  public Boolean getPublicGroup() {

    return publicGroup;
  }

  /**
   * @param publicGroup the publicGroup to set
   */
  public void setPublicGroup(Boolean publicGroup) {

    this.publicGroup = publicGroup;
  }

}
