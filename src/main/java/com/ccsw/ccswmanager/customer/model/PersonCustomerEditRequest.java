package com.ccsw.ccswmanager.customer.model;

import java.util.List;

public class PersonCustomerEditRequest {

    List<PersonCustomerDto> data;

    public List<PersonCustomerDto> getData() {
        return data;
    }

    public void setData(List<PersonCustomerDto> data) {
        this.data = data;
    }

}
