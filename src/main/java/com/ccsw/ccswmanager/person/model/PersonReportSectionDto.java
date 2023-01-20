package com.ccsw.ccswmanager.person.model;

import java.util.List;

public class PersonReportSectionDto {

    private String activityTitle;

    private List<PersonReportFactDto> facts;

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }


    public List<PersonReportFactDto> getFacts() {
        return facts;
    }

    public void setFacts(List<PersonReportFactDto> facts) {
        this.facts = facts;
    }
}
