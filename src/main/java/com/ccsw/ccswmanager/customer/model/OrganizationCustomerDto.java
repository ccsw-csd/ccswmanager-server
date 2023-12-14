package com.ccsw.ccswmanager.customer.model;

import java.util.List;

public class OrganizationCustomerDto {

    private Long id;

    private String name;

    private List<PersonCustomerWithPhotoDto> members;

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

    public List<PersonCustomerWithPhotoDto> getMembers() {
        return members;
    }

    public void setMembers(List<PersonCustomerWithPhotoDto> members) {
        this.members = members;
    }

}
