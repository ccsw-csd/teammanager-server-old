package com.ccsw.teammanager.grouplist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author apastorm
 *
 */
@Entity
@Table(name = "v_group_list")
public class GroupListEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "manager")
  private String manager;

  @Column(name = "members")
  private Long members;

  @Column(name = "subgroups")
  private Long subgroups;

  @Column(name = "external_id")
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
   * @param managers new value of {@link #getmanager}.
   */
  public void setManager(String manager) {

    this.manager = manager;
  }

  /**
   * @return members
   */
  public Long getMembers() {

    return this.members;
  }

  /**
   * @param members new value of {@link #getmembers}.
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

    return this.externalId;
  }

  /**
   * @param externalId new value of {@link #getexternalId}.
   */
  public void setExternalId(String externalId) {

    this.externalId = externalId;
  }

}