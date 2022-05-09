package com.ccsw.ccswmanager.center;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
public interface CenterRepository extends CrudRepository<CenterEntity, Long> {

    CenterEntity findById(Integer id);
}
