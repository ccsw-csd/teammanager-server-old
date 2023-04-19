package com.ccsw.teammanager.grouplist.model;

/**
 * TODO apastorm This type ...
 *
 */
public class GroupListDto {
  private Long id;

  private String name;

  private String manager;

  private Long members;

  private Long subgroups;

  private String externalId;

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
   * @return manager
   */
  public String getManager() {

    return this.manager;
  }

  /**
   * @param manager new value of {@link #getmanager}.
   */
  public void setManager(String manager) {

    this.manager = manager;
  }

  /**
   * @return users
   */
  public Long getMembers() {

    return this.members;
  }

  /**
   * @param users new value of {@link #getusers}.
   */
  public void setMembers(Long members) {

    this.members = members;
  }

  /**
   * @return subgroups
   */
  public Long getSubgroups() {

    return this.subgroups;
  }

  /**
   * @param subgroups new value of {@link #getsubgroups}.
   */
  public void setSubgroups(Long subgroups) {

    this.subgroups = subgroups;
  }

  /**
   * @return externalId
   */
  public String getExternalId() {

    return externalId;
  }

  /**
   * @param externalId new value of {@link #getexternalId}.
   */
  public void setExternalId(String externalId) {

    this.externalId = externalId;
  }

}