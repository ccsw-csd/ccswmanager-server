package com.capgemini.ccsw.ccswmanager.user.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.teams.model.TeamEntity;

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
  
  @Column(name = "username")
  private String username;
  
  @Column(name = "role")
  private String role;
  
  @OneToOne
  @JoinColumn(name="username", referencedColumnName="username", insertable = false, updatable = false)
  private PersonEntity person;
 
  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<TeamEntity> teams;
  
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
  public PersonEntity getPerson() {
	return person;
  }

  public void setPerson(PersonEntity person) {
	this.person = person;
  }

  public List<TeamEntity> getTeams() {
    return teams;
  }

  public void setTeams(List<TeamEntity> teams) {
    this.teams = teams;
  }
}
