package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;

public interface EducationCenterRepository extends CrudRepository<EducationCenterEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "province" })
    List<EducationCenterEntity> findAll();

    boolean existsEducationCenterByName(String name);
}
