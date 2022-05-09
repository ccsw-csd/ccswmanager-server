package com.ccsw.ccswmanager.center;

import java.util.List;

import com.ccsw.ccswmanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
public interface CenterService {
    List<CenterEntity> findAll();

    CenterEntity getById(Integer id);
}
