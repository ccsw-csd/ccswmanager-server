package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;

public interface VScholarRepository extends CrudRepository<VScholarEntity, Long>{

  @Override
  List<VScholarEntity> findAll();
}
