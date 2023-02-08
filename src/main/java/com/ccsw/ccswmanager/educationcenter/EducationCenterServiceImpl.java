package com.ccsw.ccswmanager.educationcenter;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationCenterServiceImpl implements EducationCenterService {

    @Autowired
    EducationCenterRepository repository;

    @Override
    public List<EducationCenterEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationCenterEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationCenterEntity save(EducationCenterEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
