package com.ccsw.ccswmanager.customer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.ccsw.ccswmanager.person.model.PersonEntity;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_manager", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> managers;

    @Formula("(select count(1) from person_customer pc where pc.customer_id = id and pc.parent_id is null)")
    private Long numberOfPersonWithoutOrganization;

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

    public List<PersonEntity> getManagers() {
        return managers;
    }

    public void setManagers(List<PersonEntity> managers) {
        this.managers = managers;
    }

    public Long getNumberOfPersonWithoutOrganization() {
        return numberOfPersonWithoutOrganization;
    }

    public void setNumberOfPersonWithoutOrganization(Long numberOfPersonWithoutOrganization) {
        this.numberOfPersonWithoutOrganization = numberOfPersonWithoutOrganization;
    }

}
