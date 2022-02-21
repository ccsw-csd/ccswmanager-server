package com.capgemini.ccsw.ccswmanager.usuario.model;

public class Usuario {

	private String id;
	
	private String username;
	
	private String role;
	
	private String name;
	
	private String lastname;
	
	/**
	   * @return id
	   */
	  public String getId() {

	    return this.id;
	  }

	  /**
	   * @param id new value of {@link #getid}.
	   */
	  public void setId(String id) {

	    this.id = id;
	  }

	  /**
	   * @return username
	   */
	  public String getUsername() {

	    return this.username;
	  }

	  /**
	   * @param username new value of {@link #getusername}.
	   */
	  public void setUsername(String username) {

	    this.username = username;
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
}
