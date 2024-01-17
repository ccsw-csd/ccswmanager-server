package com.ccsw.ccswmanager.customer.model;

import com.ccsw.ccswmanager.person.model.PersonEntity;

import javax.persistence.*;
import java.util.List;

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

}
