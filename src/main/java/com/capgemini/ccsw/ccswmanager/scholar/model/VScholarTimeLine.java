package com.capgemini.ccsw.ccswmanager.scholar.model;

import java.sql.Date;
import java.util.ArrayList;

public class VScholarTimeLine {
	private String x;
	
	private ArrayList<Long> y;
	
	private String fillColor;

	public String getX() {
		return x;
	}

	public void setX(String username) {
		this.x = username;
	}
	
	public ArrayList<Long> getY() {
		return y;
	}

	public void setY(ArrayList<Long> y) {
		this.y = y;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

}

