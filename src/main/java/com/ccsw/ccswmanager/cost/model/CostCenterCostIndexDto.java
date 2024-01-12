package com.ccsw.ccswmanager.cost.model;

import java.util.List;
import java.util.Map;

public class CostCenterCostIndexDto {

	private String costCenter;

	private List<Map<String, Double>> costIndex;

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public List<Map<String, Double>> getCostIndex() {
		return costIndex;
	}

	public void setCostIndex(List<Map<String, Double>> costIndex) {
		this.costIndex = costIndex;
	}
}
