package com.capgemini.ccsw.ccswmanager.center_transcode;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;

public interface CenterTranscodeRepository extends CrudRepository<CenterTranscodeEntity, Long>{

	CenterTranscodeEntity findById(Integer id);
	
	CenterTranscodeEntity findByName(String name);
}
