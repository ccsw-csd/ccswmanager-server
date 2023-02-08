package com.ccsw.ccswmanager.intern.model;

import com.ccsw.ccswmanager.action.model.ActionEntity;
import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.education.model.EducationEntity;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.level.model.LevelEntity;
import com.ccsw.ccswmanager.province.model.ProvinceEntity;
import com.ccsw.ccswmanager.technology.model.TechnologyEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "intern")
public class InternEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "period")
    private String period;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private EducationEntity education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_center_id")
    private EducationCenterEntity educationCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "customer")
    private String customer;

    @Column(name = "code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "intern_technology", joinColumns = @JoinColumn(name = "intern_id"), inverseJoinColumns = @JoinColumn(name = "technology_id"))
    public List<TechnologyEntity> technologies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_level_id")
    private LevelEntity englishLevel;

    @Column(name = "mentor")
    private String mentor;

    @Column(name = "coordinator")
    private String coordinator;

    @Column(name = "hr_manager")
    private String hrManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private ActionEntity action;

    @Column(name = "contract_date")
    private Date contractDate;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "link")
    private String link;

    @Column(name = "comment")
    private String comment;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EducationEntity getEducation() {
        return education;
    }

    public void setEducation(EducationEntity education) {
        this.education = education;
    }

    public EducationCenterEntity getEducationCenter() {
        return educationCenter;
    }

    public void setEducationCenter(EducationCenterEntity educationCenter) {
        this.educationCenter = educationCenter;
    }

    public CenterEntity getCenter() {
        return center;
    }

    public void setCenter(CenterEntity center) {
        this.center = center;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
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

    public List<TechnologyEntity> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyEntity> technologies) {
        this.technologies = technologies;
    }

    public LevelEntity getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(LevelEntity englishLevel) {
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

    public ActionEntity getAction() {
        return action;
    }

    public void setAction(ActionEntity action) {
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
}
