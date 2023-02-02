package com.ccsw.ccswmanager.person.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.province.model.ProvinceEntity;

/**
 * @author aolmosca
 *
 */
@Entity
@Table(name = "person")
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @Column(name = "details")
    private String details;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "department")
    private String department;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "manager")
    private String manager;

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getSaga() {

        return this.saga;
    }

    public void setSaga(String saga) {

        this.saga = saga;
    }

    public String getUsername() {

        return this.username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {

        return this.email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLastname() {

        return this.lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public CenterEntity getCenter() {

        return this.center;
    }

    public void setCenter(CenterEntity center) {

        this.center = center;
    }

    public ProvinceEntity getProvince() {


        return this.province;
    }

    public void setProvince(ProvinceEntity province) {

        this.province = province;
    }

    public String getBusinesscode() {

        return this.businesscode;
    }

    public void setBusinesscode(String businesscode) {

        this.businesscode = businesscode;
    }

    public Integer getActive() {

        return this.active;
    }

    public void setActive(Integer active) {

        this.active = active;
    }

    public String getGrade() {

        return this.grade;
    }

    public void setGrade(String grade) {

        this.grade = grade;
    }

    public String getCustomer() {

        return this.customer;
    }

    public void setCustomer(String customer) {

        this.customer = customer;
    }

    public String getRole() {

        return this.role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public String getDetails() {

        return this.details;
    }

    public void setDetails(String details) {

        this.details = details;
    }

    public Integer getHours() {

        return this.hours;
    }

    public void setHours(Integer hours) {

        this.hours = hours;
    }

    public String getDepartment() {

        return this.department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public LocalDateTime getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {

        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {

        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {

        this.updatedBy = updatedBy;
    }

    public String getManager() {

        return manager;
    }

    public void setManager(String manager) {

        this.manager = manager;
    }
}