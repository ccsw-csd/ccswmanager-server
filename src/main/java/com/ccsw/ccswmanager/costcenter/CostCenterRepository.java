package com.ccsw.ccswmanager.costcenter;

import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.costcenter.model.CostCenterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostCenterRepository extends CrudRepository<CostCenterEntity, Long> {

    @Override
    List<CostCenterEntity> findAll();

    CostCenterEntity findByName(String name);

    List<CostCenterEntity> findByCentersIn(List<CenterEntity> centers);
}
