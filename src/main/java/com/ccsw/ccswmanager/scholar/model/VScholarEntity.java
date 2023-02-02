package com.ccsw.ccswmanager.scholar.model;

import com.ccsw.ccswmanager.province.model.ProvinceEntity;

import java.util.Date;

import javax.persistence.*;

/**
 * @author jchengli
 *
 */
@Entity
@Table(name = "v_scholar")
public class VScholarEntity {

    public static final String ATT_START_DATE = "startDate";
    public static final String ATT_END_DATE = "endDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "saga")
    private String saga;

    @Column(name = "username")
    private String username;

    @Column(name = "department")
    private String department;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "customer")
    private String customer;

    @Column(name = "grade")
    private String grade;

    @Column(name = "role")
    private String role;

    @Column(name = "businesscode")
    private String businesscode;

    @Column(name = "center_id")
    private Long centerId;

    @Column(name = "email")
    private String email;

    @Column(name = "details")
    private String details;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @Column(name = "manager")
    private String manager;

    @Column(name = "scholar_id")
    private Long scholarId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "title")
    private String title;

    @Column(name = "action")
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

    public ProvinceEntity getProvince() {

        return province;
    }

    public void setProvince(ProvinceEntity province) {

        this.province = province;
    }

    public String getManager() {

        return manager;
    }

    public void setManager(String manager) {

        this.manager = manager;
    }

    /**
     * @return scholarId
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
     * @return startDate
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
     * @return endDate
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
