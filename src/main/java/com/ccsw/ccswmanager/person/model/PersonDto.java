package com.ccsw.ccswmanager.person.model;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.customer.model.CustomerSimpleDto;
import com.ccsw.ccswmanager.province.model.ProvinceDto;

import java.util.List;

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

    private ProvinceDto province;

    private String businesscode;

    private Integer active;

    private String grade;

    private List<CustomerSimpleDto> customers;

    private String role;

    private String details;

    private Integer hours;

    private String department;

    private String manager;

    private PersonSimpleDto parent;

    private Boolean delete;

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

    public CenterDto getCenter() {
        return center;
    }

    public void setCenter(CenterDto center) {
        this.center = center;
    }

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
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

    public List<CustomerSimpleDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSimpleDto> customers) {
        this.customers = customers;
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

    public PersonSimpleDto getParent() {
        return parent;
    }

    public void setParent(PersonSimpleDto parent) {
        this.parent = parent;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
