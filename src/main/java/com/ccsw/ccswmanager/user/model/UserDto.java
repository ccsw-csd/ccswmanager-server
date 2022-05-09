package com.ccsw.ccswmanager.user.model;

import java.util.List;

public class UserDto {
	
  private Long id;
	
  private String username;
	
  private String role;
	
  private String name;
	
  private String lastname;
  
  private List<String> customers;

  public Long getId() {
    return id;
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
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public List<String> getCustomers() {
    return customers;
  }

  public void setCustomers(List<String> customers) {
    this.customers = customers;
  }
}
