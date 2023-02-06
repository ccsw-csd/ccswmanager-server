package com.ccsw.ccswmanager.scholar.model;

import com.ccsw.ccswmanager.center.model.CenterEntity;
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

    @Column(name = "email")
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @Column(name = "businesscode")
    private String businesscode;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "grade")
    private String grade;

    @Column(name = "customer")
    private String customer;

    @Column(name = "role")
    private String role;

    @Column(name = "details")
    private String details;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "department")
    private String department;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaga() {
        return saga;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public CenterEntity getCenter() {
        return center;
    }

    public void setCenter(CenterEntity center) {
        this.center = center;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getScholarId() {
        return scholarId;
    }

    public void setScholarId(Long scholarId) {
        this.scholarId = scholarId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
