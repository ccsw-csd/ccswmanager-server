package com.ccsw.ccswmanager.cost;

import com.ccsw.ccswmanager.cost.model.CostEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostRepository extends CrudRepository<CostEntity, Long> {

	@Override
	@EntityGraph(attributePaths = { "costCenter" })
	List<CostEntity> findAll();

}
