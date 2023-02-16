package com.ccsw.ccswmanager.educationcenter.model;

import com.ccsw.ccswmanager.province.model.ProvinceDto;

public class EducationCenterDto {

    private Long id;

    private String name;

    private String type;

    private ProvinceDto province;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

}
