package com.capgemini.ccsw.ccswmanager.pyramid.model;

import java.util.List;

public class PyramidTeamsListDto {

    String customerName;

    List<PyramidDto> customerList;

    public PyramidTeamsListDto(String customerName, List<PyramidDto> customerList) {
        this.customerName = customerName;
        this.customerList = customerList;
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

}
