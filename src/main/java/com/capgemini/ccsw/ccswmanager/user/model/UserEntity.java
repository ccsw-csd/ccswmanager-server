package com.capgemini.ccsw.ccswmanager.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author pajimene
 *
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  
  @Column(name = "role")
  private String role;
  
  @Column(name = "username")
  private String username;
  
  @OneToOne
  @JoinColumn(name="username", referencedColumnName="username", insertable = false, updatable = false)
  private PersonEntity person;
 
  /**
   * @return id
   */
  public Long getId() {
    return this.id;
  }

  public String getUsername() {
	return username;
  }

  public PersonEntity getPerson() {
	return person;
  }

  public void setPerson(PersonEntity person) {
	this.person = person;
  }

  public void setUsername(String username) {
	this.username = username;
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
