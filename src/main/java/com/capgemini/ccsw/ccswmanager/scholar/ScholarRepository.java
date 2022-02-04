package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;


public interface ScholarRepository extends CrudRepository<ScholarEntity, Integer>{

	  @Override
	  List<ScholarEntity> findAll();

	  // SELECT * FROM scholar WHERE id = ?
	  @Query("SELECT s FROM ScholarEntity s WHERE s.id = ?1")
	  Optional<ScholarEntity> findById(Integer integer);
}
