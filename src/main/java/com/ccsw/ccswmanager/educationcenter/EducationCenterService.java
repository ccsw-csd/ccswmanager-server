package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;

public interface EducationCenterService {

    List<EducationCenterEntity> findAll();

    EducationCenterEntity getById(Long id);

    EducationCenterEntity save(EducationCenterEntity entity) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;

}
