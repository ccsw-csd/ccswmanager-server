package com.ccsw.ccswmanager.customer.model;

import java.util.List;

import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

public class CustomerDto {

    private Long id;

    private String name;

    private List<PersonSimpleDto> managers;

    private Long numberOfPersonWithoutOrganization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonSimpleDto> getManagers() {
        return managers;
    }

    public void setManagers(List<PersonSimpleDto> managers) {
        this.managers = managers;
    }

    public Long getNumberOfPersonWithoutOrganization() {
        return numberOfPersonWithoutOrganization;
    }

    public void setNumberOfPersonWithoutOrganization(Long numberOfPersonWithoutOrganization) {
        this.numberOfPersonWithoutOrganization = numberOfPersonWithoutOrganization;
    }

}
