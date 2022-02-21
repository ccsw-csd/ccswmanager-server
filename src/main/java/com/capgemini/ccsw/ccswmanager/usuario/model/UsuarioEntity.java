package com.capgemini.ccsw.ccswmanager.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pajimene
 *
 */
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username")
  private String username;
  
  @Column(name = "role")
  private String role;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "lastname")
  private String lastname;
  

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

  
  /**
   * @return role
   */
  public String getUsername() {

    return this.username;
  }
  
  /**
   * @param role new value of {@link #getrole}.
   */
  public void setUsername(String username) {

    this.username = username;
  }
  
  
  /**
   * @return role
   */
  public String getName() {

    return this.name;
  }
  /**
   * @param role new value of {@link #getrole}.
   */
  public void setName(String name) {

    this.name = name;
  }
  /**
   * @return role
   */
  public String getLastname() {

    return this.lastname;
  }
  
  /**
   * @param role new value of {@link #getrole}.
   */
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

}
