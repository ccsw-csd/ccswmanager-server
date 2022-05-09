package com.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.scholar.model.ScholarEntity;

public interface ScholarRepository extends CrudRepository<ScholarEntity, Long> {

    @Override
    List<ScholarEntity> findAll();

    ScholarEntity getByPersonId(Long id);
}
