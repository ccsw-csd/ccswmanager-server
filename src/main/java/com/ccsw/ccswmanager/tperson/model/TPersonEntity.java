package com.ccsw.ccswmanager.tperson.model;

import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.centertranscode.model.CenterTranscodeEntity;

import javax.persistence.*;

/**
 * @author dapalmie
 *
 */
@Entity
@Table(name = "t_person")
public class TPersonEntity {

    @Column(name = "saga", nullable = false)
    private String saga;

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center", referencedColumnName = "name", updatable = false)
    private CenterTranscodeEntity centerTranscode;

    @Column(name = "grade")
    private String grade;

    @Column(name = "businesscode")
    private String businesscode;

    @Column(name = "pucode")
    private String pucode;

    @Column(name = "startdate")
    private String startdate;

    @Column(name = "jobrole")
    private String jobrole;

    public String getSaga() {

        return this.saga;
    }

    public void setSaga(String saga) {

        this.saga = saga;
    }

    public String getUsername() {

        return this.username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {

        return this.email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLastname() {

        return this.lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public CenterTranscodeEntity getCenterTranscode() {

        return this.centerTranscode;
    }

    public void setCenterTranscode(CenterTranscodeEntity centerTranscode) {

        this.centerTranscode = centerTranscode;
    }

    public CenterEntity getCenter() {

        return this.centerTranscode != null ? this.centerTranscode.getCenter() : null;
    }

    public String getGrade() {

        return this.grade;
    }

    public void setGrade(String grade) {

        this.grade = grade;
    }

    public String getBusinesscode() {

        return this.businesscode;
    }

    public void setBusinesscode(String businesscode) {

        this.businesscode = businesscode;
    }

    public String getPucode() {

        return this.pucode;
    }

    public void setPucode(String pucode) {

        this.pucode = pucode;
    }

    public String getStartdate() {

        return this.startdate;
    }

    public void setStartdate(String startdate) {

        this.startdate = startdate;
    }

    public String getJobrole() {

        return this.jobrole;
    }

    public void setJobrole(String jobrole) {

        this.jobrole = jobrole;
    }

}