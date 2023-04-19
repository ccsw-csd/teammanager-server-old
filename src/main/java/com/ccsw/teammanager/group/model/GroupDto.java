package com.ccsw.teammanager.group.model;

import java.util.List;

/**
 * TODO apastorm This type ...
 *
 */
public class GroupDto {

  private Long id;

  private String name;

  private String externalId;

  private Boolean publicGroup;

  private List<GroupMemberEntity> members;

  private List<GroupManagerEntity> managers;

  private List<GroupSubgroupEntity> subgroups;

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
   * @return members
   */
  public List<GroupMemberEntity> getMembers() {

    return this.members;
  }

  /**
   * @param members new value of {@link #getmembers}.
   */
  public void setMembers(List<GroupMemberEntity> members) {

    this.members = members;
  }

  /**
   * @return managers
   */
  public List<GroupManagerEntity> getManagers() {

    return this.managers;
  }

  /**
   * @param managers new value of {@link #getmanagers}.
   */
  public void setManagers(List<GroupManagerEntity> managers) {

    this.managers = managers;
  }

  /**
   * @return subgroups
   */
  public List<GroupSubgroupEntity> getSubgroups() {

    return this.subgroups;
  }

  /**
   * @param subgroups new value of {@link #getsubgroups}.
   */
  public void setSubgroups(List<GroupSubgroupEntity> subgroups) {

    this.subgroups = subgroups;
  }

  public Boolean getPublicGroup() {

    return publicGroup;
  }

  public void setPublicGroup(Boolean publicGroup) {

    this.publicGroup = publicGroup;
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

}
