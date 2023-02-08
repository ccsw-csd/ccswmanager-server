package com.ccsw.ccswmanager.educationcenter;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EducationCenterRepository extends CrudRepository<EducationCenterEntity, Long> {

    @Override
    List<EducationCenterEntity> findAll();

}
