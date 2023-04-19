package com.ccsw.teammanager.group.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public_group")
public class PublicGroupEntity {

  @Id
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "group_id", nullable = false)
  private Long groupId;

  @Column(name = "person_id", nullable = false)
  private Long personId;

  @Column(name = "username", nullable = false)
  private String username;

  public String getId() {

    return id;
  }

  public void setId(String id) {

    this.id = id;
  }

  public Long getGroupId() {

    return groupId;
  }

  public void setGroupId(Long groupId) {

    this.groupId = groupId;
  }

  public Long getPersonId() {

    return personId;
  }

  public void setPersonId(Long personId) {

    this.personId = personId;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

}
