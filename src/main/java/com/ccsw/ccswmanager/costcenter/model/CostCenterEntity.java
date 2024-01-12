package com.ccsw.ccswmanager.costcenter.model;

import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.cost.model.CostEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cost_center")
public class CostCenterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cost_center_center", joinColumns = @JoinColumn(name = "cost_center_id"), inverseJoinColumns = @JoinColumn(name = "center_id"))
    public List<CenterEntity> centers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "costCenter", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CostEntity> costs;

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<CenterEntity> getCenters() {
        return centers;
    }

    public void setCenters(List<CenterEntity> centers) {
        this.centers = centers;
    }

    public List<CostEntity> getCosts() {
        return costs;
    }

    public void setCosts(List<CostEntity> costs) {
        this.costs = costs;
    }

    public void addCostCenterToCost() {
        if (costs != null && !costs.isEmpty()) {
            costs.forEach(cost -> cost.setCostCenter(this));
        }
    }
}
