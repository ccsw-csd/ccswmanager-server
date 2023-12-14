package com.ccsw.ccswmanager.customer.model;

import java.io.Serializable;

import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

public class PersonCustomerSimpleDto implements Serializable {

    private Long id;

    private PersonSimpleDto person;

    private CustomerSimpleDto customer;

    private PersonSimpleDto parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonSimpleDto getPerson() {
        return person;
    }

    public void setPerson(PersonSimpleDto person) {
        this.person = person;
    }

    public PersonSimpleDto getParent() {
        return parent;
    }

    public void setParent(PersonSimpleDto parent) {
        this.parent = parent;
    }

    public CustomerSimpleDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSimpleDto customer) {
        this.customer = customer;
    }

}