package com.ccsw.ccswmanager.education;

import com.ccsw.ccswmanager.education.model.EducationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository repository;

    @Override
    public List<EducationEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationEntity save(EducationEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
