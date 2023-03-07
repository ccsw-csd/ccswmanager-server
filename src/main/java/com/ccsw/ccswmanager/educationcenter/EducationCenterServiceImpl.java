package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.intern.InternService;

@Service
public class EducationCenterServiceImpl implements EducationCenterService {

    @Autowired
    EducationCenterRepository repository;

    @Autowired
    InternService internService;

    @Override
    public List<EducationCenterEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationCenterEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationCenterEntity save(EducationCenterEntity entity) throws AlreadyExistsException {

        EducationCenterEntity educationCenter = this.repository.findByName(entity.getName());

        if (educationCenter != null && (entity.getId() == null || !educationCenter.getId().equals(entity.getId()))) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws ConflictOnDeletionException {

        if (this.internService.existsByEducationCenterId(id)) {
            throw new ConflictOnDeletionException(
                    "No se puede borrar la titulación porque está relacionada con un becario");
        }
        repository.deleteById(id);
    }

}
