package com.ccsw.ccswmanager.costcenter;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.costcenter.model.CostCenterEntity;

import java.util.List;

public interface CostCenterService {

    List<CostCenterEntity> findAll();

    CostCenterEntity getById(Long id);

    CostCenterEntity save(CostCenterEntity entity) throws AlreadyExistsException;

    void deleteById(Long id);
}
