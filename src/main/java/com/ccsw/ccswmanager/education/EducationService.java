package com.ccsw.ccswmanager.education;

import java.util.List;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.education.model.EducationEntity;

public interface EducationService {

    List<EducationEntity> findAll();

    EducationEntity getById(Long id);

    EducationEntity save(EducationDto educationDto) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;

}
