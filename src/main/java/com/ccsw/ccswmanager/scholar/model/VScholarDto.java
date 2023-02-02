package com.ccsw.ccswmanager.scholar.model;

import com.ccsw.ccswmanager.province.model.ProvinceDto;

import java.util.Date;

public class VScholarDto {
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

    private Long centerId;

    private String email;

    private String details;

    private Integer hours;

    private ProvinceDto province;

    private String manager;

    private Long scholarId;

    private Date startDate;

    private Date endDate;

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
    public Long getCenterId() {
        return centerId;
    }

    /**
     * @param id new value of {@link #getcenter_id}.
     */
    public void setCenterId(Long centerId) {
        this.centerId = centerId;
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

    public ProvinceDto getProvince() {

        return province;
    }

    public void setProvince(ProvinceDto province) {

        this.province = province;
    }

    public String getManager() {

        return manager;
    }

    public void setManager(String manager) {

        this.manager = manager;
    }

    /**
     * @return scholar_id
     */
    public Long getScholarId() {
        return scholarId;
    }

    /**
     * @param id new value of {@link #getscholar_id}.
     */
    public void setScholarId(Long scholarId) {
        this.scholarId = scholarId;
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param id new value of {@link #getstart_date}.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return end_date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param id new value of {@link #getend_date}.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
