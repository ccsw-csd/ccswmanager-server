package com.capgemini.ccsw.ccswmanager.centertranscode;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.centertranscode.model.CenterTranscodeEntity;

public interface CenterTranscodeRepository extends CrudRepository<CenterTranscodeEntity, Long> {

    CenterTranscodeEntity findById(Integer id);

    CenterTranscodeEntity findByName(String name);

    @EntityGraph(attributePaths = { "center" })
    List<CenterTranscodeEntity> findAll();
}
