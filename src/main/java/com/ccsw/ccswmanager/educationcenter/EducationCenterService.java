package com.ccsw.ccswmanager.educationcenter;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;

import java.util.List;


public interface EducationCenterService {

    List<EducationCenterEntity> findAll();

    EducationCenterEntity getById(Long id);

    EducationCenterEntity save(EducationCenterEntity entity);

    void deleteById(Long id);

}
