package com.capgemini.ccsw.ccswmanager.scholar.model;

import com.capgemini.ccsw.ccswmanager.center.model.CenterDto;

import java.sql.Date;

import javax.persistence.Column;

public class ScholarDto {
  private Long id;

  private String saga;
  
  private String username;
  
  private String department;
	
  private String name;

  private String lastname;
  
  private Integer active;
	
  private String customer;
  
  private String grade;
  
  private String role;
  
  private String businesscode;
  
  private Long center_id;
  
  private String email;

  private String details;
  
  private Integer hours;
  
  private Long scholar_id;

  private Date start_date;

  private Date end_date;

  private String title;

  private Integer action;

  
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id new value of {@link #getid}.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return saga
	 */
	public String getSaga() {
		return saga;
	}
	
	/**
	 * @param saga new value of {@link #getsaga}.
	 */
	public void setSaga(String saga) {
		this.saga = saga;
	}
	
	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param id new value of {@link #getusername}.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return department
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * @param id new value of {@link #getdepartment}.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param id new value of {@link #getname}.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * @param id new value of {@link #getlastname}.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * @return active
	 */
	public Integer getActive() {
		return active;
	}
	
	/**
	 * @param id new value of {@link #getactive}.
	 */
	public void setActive(Integer active) {
		this.active = active;
	}
	
	/**
	 * @return customer
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * @param id new value of {@link #getcustomer}.
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}
	
	/**
	 * @param id new value of {@link #getgrade}.
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * @param id new value of {@link #getrole}.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @return businesscode
	 */
	public String getBusinesscode() {
		return businesscode;
	}
	
	/**
	 * @param id new value of {@link #getbusinesscode}.
	 */
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	
	/**
	 * @return center_id
	 */
	public Long getCenter_id() {
		return center_id;
	}
	
	/**
	 * @param id new value of {@link #getcenter_id}.
	 */
	public void setCenter_id(Long center_id) {
		this.center_id = center_id;
	}
	
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param id new value of {@link #getemail}.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return details
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * @param id new value of {@link #getdetails}.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * @return hours
	 */
	public Integer getHours() {
		return hours;
	}
	
	/**
	 * @param id new value of {@link #gethours}.
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	/**
	 * @return scholar_id
	 */
	public Long getScholar_id() {
		return scholar_id;
	}
	
	/**
	 * @param id new value of {@link #getscholar_id}.
	 */
	public void setScholar_id(Long scholar_id) {
		this.scholar_id = scholar_id;
	}
	
	/**
	 * @return start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	
	/**
	 * @param id new value of {@link #getstart_date}.
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	/**
	 * @return end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @param id new value of {@link #getend_date}.
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param id new value of {@link #gettitle}.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return action
	 */
	public Integer getAction() {
		return action;
	}
	
	/**
	 * @param id new value of {@link #getaction}.
	 */
	public void setAction(Integer action) {
		this.action = action;
	}
}
