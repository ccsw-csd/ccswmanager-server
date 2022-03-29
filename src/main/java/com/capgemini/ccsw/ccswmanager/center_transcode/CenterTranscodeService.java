package com.capgemini.ccsw.ccswmanager.center_transcode;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;

public interface CenterTranscodeService {

	CenterTranscodeEntity getById(Integer id);
	
	CenterTranscodeEntity findByName(String name);
	
	List<CenterTranscodeEntity> findAll();
	
	void save(CenterTranscodeEntity center);
}
