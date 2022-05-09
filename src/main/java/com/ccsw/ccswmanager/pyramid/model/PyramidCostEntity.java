package com.ccsw.ccswmanager.pyramid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jchengli
 *
 */
@Entity
@Table(name = "pyramid_cost")
public class PyramidCostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "grade")
    private String grade;

    @Column(name = "cost")
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
