package com.ccsw.ccswmanager.customer.model;

import com.ccsw.ccswmanager.person.model.PersonDto;

public class PersonCustomerDto {

    private Long id;

    private PersonDto person;

    private PersonDto parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public PersonDto getParent() {
        return parent;
    }

    public void setParent(PersonDto parent) {
        this.parent = parent;
    }

}