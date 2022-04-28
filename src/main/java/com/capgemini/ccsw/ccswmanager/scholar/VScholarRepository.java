package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;

/**
 * @author jchengli
 *
 */

public interface VScholarRepository
        extends CrudRepository<VScholarEntity, Long>, JpaSpecificationExecutor<VScholarEntity> {

    @Override
    List<VScholarEntity> findAll();

}
