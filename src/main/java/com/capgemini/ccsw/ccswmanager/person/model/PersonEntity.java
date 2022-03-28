package com.capgemini.ccsw.ccswmanager.person.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;
import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;

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

    @ManyToOne
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

    @Column(name = "details")
    private String details;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "department")
    private String department;

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
     * @return centerId
     */
    public CenterEntity getCenter() {

        return this.center;
    }

    /**
     * @param centeId new value of {@link #getcenter_id}.
     */
    public void setCenter(CenterEntity center) {

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
     * @return
     */
    public String getGrade() {

        return this.grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {

        this.grade = grade;
    }

    /**
     * @return
     */
    public String getCustomer() {

        return this.customer;
    }

    /**
     * @param customer
     */
    public void setCustomer(String customer) {

        this.customer = customer;
    }

    /**
     * @return
     */
    public String getRole() {

        return this.role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {

        this.role = role;
    }

    /**
     * @return
     */
    public String getDetails() {

        return this.details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {

        this.details = details;
    }

    /**
     * @return
     */
    public Integer getHours() {

        return this.hours;
    }

    /**
     * @param hours
     */
    public void setHours(Integer hours) {

        this.hours = hours;
    }

    /**
     * @return
     */
    public String getDepartment() {

        return this.department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {

        this.department = department;
    }

}