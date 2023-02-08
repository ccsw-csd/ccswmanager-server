package com.ccsw.ccswmanager.intern;

import com.ccsw.ccswmanager.intern.model.InternEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternServiceImpl implements InternService {

    @Autowired
    InternRepository repository;

    @Override
    public List<InternEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public InternEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public InternEntity save(InternEntity entity) {

        return repository.save(entity);
    }

    @Override
    public List<InternEntity> saveAll(List<InternEntity> entities) {

        repository.saveAll(entities);

        return findAll();
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<InternEntity> entities) {

        repository.deleteAll(entities);
    }

}
