package com.ccsw.ccswmanager.education;

import com.ccsw.ccswmanager.education.model.EducationEntity;

import java.util.List;


public interface EducationService {

    List<EducationEntity> findAll();

    EducationEntity getById(Long id);

    EducationEntity save(EducationEntity entity);

    void deleteById(Long id);

}
