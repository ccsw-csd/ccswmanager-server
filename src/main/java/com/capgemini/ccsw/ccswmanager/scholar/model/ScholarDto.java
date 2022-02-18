package com.capgemini.ccsw.ccswmanager.scholar.model;

import com.capgemini.ccsw.ccswmanager.center.model.CenterDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

import java.sql.Date;

import javax.persistence.Column;


/**
 * @author jchengli
 *
 */
public class ScholarDto {
  private Long id;
  
  private PersonEntity person;
  
  private Date start_date;

  private Date end_date;

  private String title;

  private Integer action;
  
  private String username;
	
  private String name;

  private String lastname;
  
  private String customer;

  private String details;
  
  private Integer hours;
  
  private Integer active;
  
  
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
	 * @return person_id
	 */
	public PersonEntity getPerson_id() {
		return person;
	}
	
	/**
	 * @param id new value of {@link #person_id}.
	 */
	public void setPerson_id(PersonEntity person) {
		this.person = person;
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

}
