package com.ccsw.ccswmanager.customer.model;

import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

public class PersonCustomerWithPhotoDto {

    private Long id;

    private PersonSimpleDto person;

    private Long parent;

    private String photo;

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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getPhoto() {

        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}