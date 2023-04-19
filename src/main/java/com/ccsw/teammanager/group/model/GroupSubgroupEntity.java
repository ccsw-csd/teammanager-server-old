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
@Table(name = "group_subgroup")
public class GroupSubgroupEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "group_id", nullable = false)
  private Long group_id;

  @Column(name = "subgroup_id", nullable = false)
  private Long subgroup_id;

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
   * @return group_id
   */
  public Long getGroup_id() {

    return this.group_id;
  }

  /**
   * @param group_id new value of {@link #getgroup_id}.
   */
  public void setGroup_id(Long group_id) {

    this.group_id = group_id;
  }

  /**
   * @return subgroup_id
   */
  public Long getSubgroup_id() {

    return this.subgroup_id;
  }

  /**
   * @param subgroup_id new value of {@link #getsubgroup_id}.
   */
  public void setSubgroup_id(Long subgroup_id) {

    this.subgroup_id = subgroup_id;
  }

}