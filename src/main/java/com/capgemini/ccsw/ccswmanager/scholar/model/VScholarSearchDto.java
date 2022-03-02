package com.capgemini.ccsw.ccswmanager.scholar.model;

import java.sql.Date;

/**
* @author jchengli
*/

public class VScholarSearchDto {

	private Date startDate;
	
	private Date endDate;

	
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
	
}
