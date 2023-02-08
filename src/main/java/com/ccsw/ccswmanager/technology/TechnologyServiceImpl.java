package com.ccsw.ccswmanager.technology;

import com.ccsw.ccswmanager.technology.model.TechnologyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository repository;

    @Override
    public List<TechnologyEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public TechnologyEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public TechnologyEntity save(TechnologyEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
