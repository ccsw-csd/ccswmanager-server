package com.ccsw.ccswmanager.intern;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.ccswmanager.intern.model.InternEntity;

public interface InternRepository extends CrudRepository<InternEntity, Long>, JpaSpecificationExecutor<InternEntity> {

    @Override
    @EntityGraph(attributePaths = { "education", "educationCenter", "center", "province", "technologies", "englishLevel", "action" })
    List<InternEntity> findAll();

    @EntityGraph(attributePaths = { "education", "educationCenter", "center", "province", "technologies", "englishLevel", "action" })
    List<InternEntity> findByUsernameIsNotNullAndUsernameIsNotAndActive(String username, Integer active);

    @Query("SELECT i FROM InternEntity i WHERE i.education.id = :id")
    InternEntity findByEducationId(@Param("id") Long id);

}
