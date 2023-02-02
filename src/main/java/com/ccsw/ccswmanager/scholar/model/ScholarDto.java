package com.ccsw.ccswmanager.scholar.model;

import java.util.Date;

import com.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author jchengli
 *
 */
public class ScholarDto {

    private Long id;

    private PersonEntity person;

    private Date startDate;

    private Date endDate;

    private String title;

    private Integer action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
