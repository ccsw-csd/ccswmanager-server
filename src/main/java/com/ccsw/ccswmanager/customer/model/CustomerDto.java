package com.ccsw.ccswmanager.customer.model;

import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

import java.util.List;

public class CustomerDto {

    private Long id;

    private String name;

    private List<PersonSimpleDto> managers;

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

}
