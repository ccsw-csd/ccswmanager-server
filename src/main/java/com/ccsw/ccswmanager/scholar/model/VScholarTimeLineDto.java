package com.ccsw.ccswmanager.scholar.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jchengli
 *
 */

public class VScholarTimeLineDto {
    @JsonProperty("x")
    private String axisX;

    @JsonProperty("y")
    private List<Long> axisY;

    private String fillColor;

    public String getAxisX() {
        return axisX;
    }

    public void setAxisX(String username) {
        this.axisX = username;
    }

    public List<Long> getAxisY() {
        return axisY;
    }

    public void setAxisY(List<Long> axisY) {
        this.axisY = axisY;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

}
