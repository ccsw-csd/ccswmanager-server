package com.capgemini.ccsw.ccswmanager.center_transcode;

import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;

public interface CenterTranscodeService {

	CenterTranscodeEntity getById(Integer id);
	
	CenterTranscodeEntity findByName(String name);
}
