package com.ccsw.ccswmanager.centertranscode;

import com.ccsw.ccswmanager.center.model.CenterEntity;

public interface CenterTranscodeService {

    CenterEntity findByName(String name);
}
