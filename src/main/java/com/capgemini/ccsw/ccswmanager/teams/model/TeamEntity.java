package com.capgemini.ccsw.ccswmanager.teams.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;

@Entity
@Table(name = "team")
public class TeamEntity implements Serializable{
  
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long Id;
  
  @Column(name = "customer")
  private String customer;
 
  @ManyToOne
  @JoinColumn(name="username_id", nullable = false)
  private UserEntity user;
  
  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }
  
}
