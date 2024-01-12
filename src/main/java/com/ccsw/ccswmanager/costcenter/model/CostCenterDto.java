package com.ccsw.ccswmanager.costcenter.model;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.cost.model.CostDto;

import java.util.List;

public class CostCenterDto {

    private Long id;

    private String name;

    public List<CenterDto> centers;

    private List<CostDto> costs;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CenterDto> getCenters() {
        return centers;
    }

    public void setCenters(List<CenterDto> centers) {
        this.centers = centers;
    }

    public List<CostDto> getCosts() {
        return costs;
    }

    public void setCosts(List<CostDto> costs) {
        this.costs = costs;
    }
}
