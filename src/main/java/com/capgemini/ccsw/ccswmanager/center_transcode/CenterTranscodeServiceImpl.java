package com.capgemini.ccsw.ccswmanager.center_transcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;

@Service
public class CenterTranscodeServiceImpl implements CenterTranscodeService{

	@Autowired
	private CenterTranscodeRepository centerTranscodeRepository;
	
	@Override
	public CenterTranscodeEntity getById(Integer id) {
		return centerTranscodeRepository.findById(id);
	}
	public CenterTranscodeEntity findByName(String name) {
		return centerTranscodeRepository.findByName(name);
	}
	public List<CenterTranscodeEntity> findAll(){
		return (List<CenterTranscodeEntity>) centerTranscodeRepository.findAll();
	}
	public void save(CenterTranscodeEntity center) {
		centerTranscodeRepository.save(center);
	}

}
