package com.ccsw.ccswmanager.cost.model;

public class PyramidGraphDto {

    private String grade;

    private Long count;

    private Double index;

    public PyramidGraphDto(String grade, Long count, Double index) {
        this.grade = grade;
        this.count = count;
        this.index = index;
    }

    public PyramidGraphDto() {
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

}
