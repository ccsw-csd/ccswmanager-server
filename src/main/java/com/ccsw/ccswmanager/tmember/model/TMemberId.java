package com.ccsw.ccswmanager.tmember.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TMemberId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userCn;
    private String groupCn;

    public String getUserCn() {
        return userCn;
    }

    public void setUserCn(String userCn) {
        this.userCn = userCn;
    }

    public String getGroupCn() {
        return groupCn;
    }

    public void setGroupCn(String groupCn) {
        this.groupCn = groupCn;
    }

}
