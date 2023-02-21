package com.ccsw.ccswmanager.intern.model;

import com.ccsw.ccswmanager.action.model.ActionDto;
import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterDto;
import com.ccsw.ccswmanager.level.model.LevelDto;
import com.ccsw.ccswmanager.province.model.ProvinceDto;
import com.ccsw.ccswmanager.technology.model.TechnologyDto;

import java.util.Date;
import java.util.List;

public class InternDto {

    private Long id;

    private String period;

    private String username;

    private String name;

    private String lastname;

    private Integer gender;

    private String email;

    private EducationDto education;

    private EducationCenterDto educationCenter;

    private CenterDto center;

    private ProvinceDto province;

    private Date startDate;

    private Date endDate;

    private Integer hours;

    private String customer;

    private String code;

    private List<TechnologyDto> technologies;

    private LevelDto englishLevel;

    private String mentor;

    private String coordinator;

    private String hrManager;

    private ActionDto action;

    private Date contractDate;

    private Integer active;

    private String link;

    private String comment;

    private Boolean delete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EducationDto getEducation() {
        return education;
    }

    public void setEducation(EducationDto education) {
        this.education = education;
    }

    public EducationCenterDto getEducationCenter() {
        return educationCenter;
    }

    public void setEducationCenter(EducationCenterDto educationCenter) {
        this.educationCenter = educationCenter;
    }

    public CenterDto getCenter() {
        return center;
    }

    public void setCenter(CenterDto center) {
        this.center = center;
    }

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TechnologyDto> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyDto> technologies) {
        this.technologies = technologies;
    }

    public LevelDto getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(LevelDto englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getHrManager() {
        return hrManager;
    }

    public void setHrManager(String hrManager) {
        this.hrManager = hrManager;
    }

    public ActionDto getAction() {
        return action;
    }

    public void setAction(ActionDto action) {
        this.action = action;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDelete() {
        return delete != null ? delete : false;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
