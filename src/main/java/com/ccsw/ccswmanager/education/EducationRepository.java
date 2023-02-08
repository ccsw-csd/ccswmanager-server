package com.ccsw.ccswmanager.education;

import com.ccsw.ccswmanager.education.model.EducationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EducationRepository extends CrudRepository<EducationEntity, Long> {

    @Override
    List<EducationEntity> findAll();

}
