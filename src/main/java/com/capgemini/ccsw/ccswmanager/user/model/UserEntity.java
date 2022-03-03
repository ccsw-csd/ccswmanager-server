package com.capgemini.ccsw.ccswmanager.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author pajimene
 *
 */
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  
  @Column(name = "role")
  private String role;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="username")
  private PersonEntity username;

 
  /**
   * @return id
   */
  public Long getId() {

    return this.id;
  }

  public PersonEntity getPerson() {
	return username;
}

public void setPerson(PersonEntity person) {
	this.username = person;
}

/**
   * @param id new value of {@link #getid}.
   */
  public void setId(Long id) {

    this.id = id;
  }


  /**
   * @return role
   */
  public String getRole() {

    return this.role;
  }

  /**
   * @param role new value of {@link #getrole}.
   */
  public void setRole(String role) {

    this.role = role;
  }

}
