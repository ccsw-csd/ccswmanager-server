package com.ccsw.ccswmanager.pyramid;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;

/**
 * @author jchengli
 *
 */

public interface PyramidRepository extends CrudRepository<PyramidCostEntity, Long> {
    @Override
    List<PyramidCostEntity> findAll();

    PyramidCostEntity getByGrade(String grade);
}
