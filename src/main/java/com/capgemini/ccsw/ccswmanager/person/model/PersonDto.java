package com.capgemini.ccsw.ccswmanager.person.model;

import com.capgemini.ccsw.ccswmanager.center.model.CenterDto;

/**
 * @author aolmosca
 *
 */
public class PersonDto {
  private Long id;

  private String saga;

  private String username;

  private String email;

  private String name;

  private String lastname;

  private CenterDto center;

  private String businesscode;

  private Integer active;

  private String grade;

  private String customer;

  private String role;

  private String details;

  private Integer hours;

  private String department;

  private Boolean delete;

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
   * @return saga
   */
  public String getSaga() {

    return this.saga;
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

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return email
   */
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getemail}.
   */
  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return lastname
   */
  public String getLastname() {

    return this.lastname;
  }

  /**
   * @param lastname new value of {@link #getlastname}.
   */
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  /**
   * @return center_id
   */
  public CenterDto getCenter() {

    return this.center;
  }

  /**
   * @param center_id new value of {@link #getcenter_id}.
   */
  public void setCenter(CenterDto center) {

    this.center = center;
  }

  /**
   * @return businesscode
   */
  public String getBusinesscode() {

    return this.businesscode;
  }

  /**
   * @param businesscode new value of {@link #getbusinesscode}.
   */
  public void setBusinesscode(String businesscode) {

    this.businesscode = businesscode;
  }

  /**
   * @return active
   */
  public Integer getActive() {

    return this.active;
  }

  /**
   * @param active new value of {@link #getactive}.
   */
  public void setActive(Integer active) {

    this.active = active;
  }

  /**
   * @return the grade
   */
  public String getGrade() {

    return this.grade;
  }

  /**
   * @param grade the grade to set
   */
  public void setGrade(String grade) {

    this.grade = grade;
  }

  /**
   * @return customer
   */
  public String getCustomer() {

    return this.customer;
  }

  /**
   * @param customer new value of {@link #getcustomer}.
   */
  public void setCustomer(String customer) {

    this.customer = customer;
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
   * @return details
   */
  public String getDetails() {

    return this.details;
  }

  /**
   * @param details new value of {@link #getdetails}.
   */
  public void setDetails(String details) {

    this.details = details;
  }

  /**
   * @return hours
   */
  public Integer getHours() {

    return this.hours;
  }

  /**
   * @param hours new value of {@link #gethours}.
   */
  public void setHours(Integer hours) {

    this.hours = hours;
  }

  /**
   * @return department
   */
  public String getDepartment() {

    return this.department;
  }

  /**
   * @param department new value of {@link #getdepartment}.
   */
  public void setDepartment(String department) {

    this.department = department;
  }

  /**
   * @return delete
   */
  public Boolean getDelete() {

    return delete;
  }

  /**
   * @param delete new value of {@link #getdelete}.
   */
  public void setDelete(Boolean delete) {

    this.delete = delete;
  }

}
