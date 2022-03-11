package com.capgemini.ccsw.ccswmanager.scholar.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jchengli
 *
 */

public class VScholarTimeLine {
    @JsonProperty("x")
    private String axisX;

    @JsonProperty("y")
    private ArrayList<Long> axisY;

    private String fillColor;

    public String getX() {
        return axisX;
    }

    public void setX(String username) {
        this.axisX = username;
    }

    public ArrayList<Long> getY() {
        return axisY;
    }

    public void setY(ArrayList<Long> axisY) {
        this.axisY = axisY;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

}
