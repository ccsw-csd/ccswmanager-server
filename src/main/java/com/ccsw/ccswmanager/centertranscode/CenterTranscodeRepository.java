package com.ccsw.ccswmanager.centertranscode;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.centertranscode.model.CenterTranscodeEntity;

public interface CenterTranscodeRepository extends CrudRepository<CenterTranscodeEntity, Long> {

    CenterTranscodeEntity findByName(String name);
}
