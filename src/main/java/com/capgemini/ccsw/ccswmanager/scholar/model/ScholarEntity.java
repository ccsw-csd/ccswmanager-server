package com.capgemini.ccsw.ccswmanager.scholar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;

@Entity
@Table(name = "scholar")
public class ScholarEntity {	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "person_id", nullable = false)
  private Integer person_id;
  
  @Column(name = "username", nullable = false)
  private String username;
	
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "lastname", nullable = false)
  private String lastname;
	
  @Column(name = "customer", nullable = false)
  private String customer;
	
  @Column(name = "hours", nullable = false)
  private Integer hours;

  @Column(name = "details", nullable = false)
  private String details;
	
  @Column(name = "start_date", nullable = false)
  private Date start_date;

  @Column(name = "end_date", nullable = false)
  private Date end_date;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "action", nullable = false)
  private String action;
  
  @Column(name = "active", nullable = false)
  private Integer active;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the person_id
	 */
	public Integer getPerson_id() {
		return person_id;
	}
	
	/**
	 * @param person_id the person_id to set
	 */
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}
	
	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	/**
	 * @return the detail
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * @param detail the detail to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
	
	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

	public ScholarEntity save(ScholarEntity scholar) {
		// TODO Auto-generated method stub
		return null;
	}
}
