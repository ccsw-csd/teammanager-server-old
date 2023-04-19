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
@Table(name = "group_manager")
public class GroupManagerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "group_id", nullable = false)
  private Long group_id;

  @Column(name = "person_id", nullable = false)
  private Long person_id;

  /**
   * @return person_id
   */
  public Long getPerson_id() {

    return this.person_id;
  }

  /**
   * @param person_id new value of {@link #getperson_id}.
   */
  public void setPerson_id(Long person_id) {

    this.person_id = person_id;
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

}
