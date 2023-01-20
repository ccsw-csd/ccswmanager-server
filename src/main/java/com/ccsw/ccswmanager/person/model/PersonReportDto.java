package com.ccsw.ccswmanager.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonReportDto {

    @JsonProperty("@type")
    private String type;

    private String summary;

    private List<PersonReportSectionDto> sections;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<PersonReportSectionDto> getSections() {
        return sections;
    }

    public void setSections(List<PersonReportSectionDto> sections) {
        this.sections = sections;
    }

}
