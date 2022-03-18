package com.capgemini.ccsw.ccswmanager.tperson.model;

import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;

public class TPersonDto {

    private String saga;

    private String username;

    private String email;

    private String name;

    private String lastname;

    private CenterEntity center;

    private String grade;

    private String businesscode;

    private String pucode;

    private String startdate;

    private String jobrole;

    /**
     * @return
     */
    public String getSaga() {

        return this.saga;
    }

    /**
     * @param saga
     */
    public void setSaga(String saga) {

        this.saga = saga;
    }

    /**
     * @return
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @return email
     */
    public String getEmail() {

        return this.email;
    }

    /**
     * @param email new value of {@link #getemail}.
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * @return name
     */
    public String getName() {

        return this.name;
    }

    /**
     * @param name new value of {@link #getname}.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return lastname
     */
    public String getLastname() {

        return this.lastname;
    }

    /**
     * @param lastname new value of {@link #getlastname}.
     */
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    /**
     * @return center
     */
    public CenterEntity getCenter() {

        return this.center;
    }

    /**
     * @param center new value of {@link #getcenter}.
     */
    public void setCenter(CenterEntity center) {

        this.center = center;
    }

    /**
     * @return grade
     */
    public String getGrade() {

        return this.grade;
    }

    /**
     * @param grade new value of {@link #getgrade}.
     */
    public void setGrade(String grade) {

        this.grade = grade;
    }

    /**
     * @return businesscode
     */
    public String getBusinesscode() {

        return this.businesscode;
    }

    /**
     * @param businesscode new value of {@link #getbusinesscode}.
     */
    public void setBusinesscode(String businesscode) {

        this.businesscode = businesscode;
    }

    /**
     * @return pucode
     */
    public String getPucode() {

        return this.pucode;
    }

    /**
     * @param pucode new value of {@link #getpucode}.
     */
    public void setPucode(String pucode) {

        this.pucode = pucode;
    }

    /**
     * @return startdate
     */
    public String getStartdate() {

        return this.startdate;
    }

    /**
     * @param startdate new value of {@link #getstartdate}.
     */
    public void setStartdate(String startdate) {

        this.startdate = startdate;
    }

    /**
     * @return role
     */
    public String getJobrole() {

        return this.jobrole;
    }

    /**
     * @param role new value of {@link #getrole}.
     */
    public void setJobrole(String jobrole) {

        this.jobrole = jobrole;
    }

}