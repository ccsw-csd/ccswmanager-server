package com.capgemini.ccsw.ccswmanager.centertranscode;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.centertranscode.model.CenterTranscodeEntity;

public interface CenterTranscodeRepository extends CrudRepository<CenterTranscodeEntity, Long>{

	CenterTranscodeEntity findById(Integer id);
	
	CenterTranscodeEntity findByName(String name);
}
