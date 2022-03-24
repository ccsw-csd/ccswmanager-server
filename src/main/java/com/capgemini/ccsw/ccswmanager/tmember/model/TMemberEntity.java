package com.capgemini.ccsw.ccswmanager.tmember.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

@Entity
@Table(name = "t_members")
public class TMemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TMemberId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCn", referencedColumnName = "username", insertable = false, updatable = false)
    private TPersonEntity tperson;

    public TPersonEntity getTperson() {
        return tperson;
    }

    public void setTperson(TPersonEntity tperson) {
        this.tperson = tperson;
    }

    public String getUserCn() {
        return id.getUserCn();
    }

    public void setUserCn(String userCn) {
        this.id.setUserCn(userCn);
    }

    public String getGroupCn() {
        return id.getGroupCn();
    }

    public void setGroupCn(String groupCn) {
        this.id.setGroupCn(groupCn);
    }

    public TMemberId getId() {
        return id;
    }

    public void setId(TMemberId id) {
        this.id = id;
    }

}
