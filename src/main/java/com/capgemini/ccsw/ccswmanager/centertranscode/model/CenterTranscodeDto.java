package com.capgemini.ccsw.ccswmanager.centertranscode.model;

import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;

public class CenterTranscodeDto {

    private Long id;

    private String name;

    private CenterEntity center;

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

    public CenterEntity getCenter() {
        return center;
    }

    public void setCenter(CenterEntity center) {
        this.center = center;
    }

}
