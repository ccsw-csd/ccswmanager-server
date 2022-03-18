package com.capgemini.ccsw.ccswmanager.center;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
public interface CenterRepository extends CrudRepository<CenterEntity, Long> {
    @EntityGraph(attributePaths = { "id" })
    CenterEntity findById(Integer id);
}
