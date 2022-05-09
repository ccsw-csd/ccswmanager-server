package com.ccsw.ccswmanager.pyramid.model;

/**
 * @author jchengli
 *
 */
public class PyramidCostDto {
    private Long id;

    private String grade;

    private Double cost;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getid}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade new value of {@link #getGrade}.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost new value of {@link #getCost}.
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

}
