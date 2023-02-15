package com.ccsw.ccswmanager.intern;

import com.ccsw.ccswmanager.intern.model.InternEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface InternRepository extends CrudRepository<InternEntity, Long>, JpaSpecificationExecutor<InternEntity> {

    @Override
    @EntityGraph(attributePaths = { "education", "educationCenter", "center", "province", "technologies", "englishLevel", "action" })
    List<InternEntity> findAll();

    @EntityGraph(attributePaths = { "education", "educationCenter", "center", "province", "technologies", "englishLevel", "action" })
    List<InternEntity> findByUsernameIsNotNullAndUsernameIsNotAndActive(String username, Integer active);

}
