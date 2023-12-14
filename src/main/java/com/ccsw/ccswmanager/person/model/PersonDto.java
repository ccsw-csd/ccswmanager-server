package com.ccsw.ccswmanager.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.customer.model.CustomerSimpleDto;
import com.ccsw.ccswmanager.customer.model.PersonCustomerSimpleDto;
import com.ccsw.ccswmanager.province.model.ProvinceDto;

/**
 * @author aolmosca
 *
 */
public class PersonDto implements Serializable {

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

    private List<PersonCustomerSimpleDto> personCustomers = new ArrayList<>();

    private String parents;

    private String role;

    private String details;

    private Integer hours;

    private String department;

    private String manager;

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
        if (personCustomers == null)
            return null;

        return personCustomers.stream().map(item -> item.getCustomer()).collect(Collectors.toList());
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

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public List<PersonCustomerSimpleDto> getPersonCustomers() {
        return personCustomers;
    }

    public void setPersonCustomers(List<PersonCustomerSimpleDto> personCustomers) {
        this.personCustomers = personCustomers;
    }

}
