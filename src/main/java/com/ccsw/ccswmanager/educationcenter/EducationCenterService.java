package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.utils.ItemInUseException;

public interface EducationCenterService {

    List<EducationCenterEntity> findAll();

    EducationCenterEntity getById(Long id);

    EducationCenterEntity save(EducationCenterEntity entity);

    void deleteById(Long id) throws ItemInUseException;

}
