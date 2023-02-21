package com.ccsw.ccswmanager.center;

import com.ccsw.ccswmanager.center.model.CenterEntity;

import java.util.List;

/**
 * @author aolmosca
 *
 */
public interface CenterService {

    List<CenterEntity> findAll();

    CenterEntity getById(Integer id);
}
