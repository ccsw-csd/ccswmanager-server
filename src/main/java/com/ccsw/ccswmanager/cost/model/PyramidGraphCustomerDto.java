package com.ccsw.ccswmanager.cost.model;

import java.util.List;

public class PyramidGraphCustomerDto {

    private String customer;

    private List<PyramidGraphDto> data;

    public PyramidGraphCustomerDto(String customer, List<PyramidGraphDto> data) {
        this.customer = customer;
        this.data = data;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<PyramidGraphDto> getData() {
        return data;
    }

    public void setData(List<PyramidGraphDto> data) {
        this.data = data;
    }
}
