package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;

public interface ScholarRepository extends CrudRepository<ScholarEntity, Long> {

    @Override
    List<ScholarEntity> findAll();

    @EntityGraph(attributePaths = { "person" })
    ScholarEntity getByPerson_Id(Long id);
}
