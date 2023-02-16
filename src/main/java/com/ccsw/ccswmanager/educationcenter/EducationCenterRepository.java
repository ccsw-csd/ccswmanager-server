package com.ccsw.ccswmanager.educationcenter;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EducationCenterRepository extends CrudRepository<EducationCenterEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "province" })
    List<EducationCenterEntity> findAll();

}
