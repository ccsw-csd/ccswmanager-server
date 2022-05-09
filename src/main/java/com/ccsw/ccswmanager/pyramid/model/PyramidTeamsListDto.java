package com.ccsw.ccswmanager.pyramid.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PyramidTeamsListDto {

    private String customerName;

    private List<PyramidDto> customerList;

    @JsonIgnoreProperties
    private Long customerSize;

    public PyramidTeamsListDto(String customerName, List<PyramidDto> customerList, Long customerSize) {
        this.customerName = customerName;
        this.customerList = customerList;
        this.customerSize = customerSize;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<PyramidDto> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<PyramidDto> customerList) {
        this.customerList = customerList;
    }

    public Long getCustomerSize() {
        return customerSize;
    }

    public void setCustomerSize(Long customerSize) {
        this.customerSize = customerSize;
    }

}
