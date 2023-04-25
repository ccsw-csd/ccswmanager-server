package com.ccsw.ccswmanager.centertranscode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.center.model.CenterEntity;

@Service
public class CenterTranscodeServiceImpl implements CenterTranscodeService {

    @Autowired
    CenterTranscodeRepository repository;

    @Override
    public CenterEntity findByName(String name) {
        return this.repository.findByName(name).getCenter();
    }
}
