package com.ccsw.ccswmanager.education;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.education.model.EducationEntity;

public interface EducationRepository extends CrudRepository<EducationEntity, Long> {

    @Override
    List<EducationEntity> findAll();

    EducationEntity getByName(String name);

    boolean existsByName(String name);

}
