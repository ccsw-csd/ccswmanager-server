package com.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.scholar.model.VScholarEntity;

/**
 * @author jchengli
 *
 */
public interface VScholarRepository extends CrudRepository<VScholarEntity, Long>, JpaSpecificationExecutor<VScholarEntity> {

    @Override
    @EntityGraph(attributePaths = { "center", "province" })
    List<VScholarEntity> findAll();

}
