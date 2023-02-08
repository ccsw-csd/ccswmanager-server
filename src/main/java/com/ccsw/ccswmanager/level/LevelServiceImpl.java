package com.ccsw.ccswmanager.level;

import com.ccsw.ccswmanager.level.model.LevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    LevelRepository repository;

    @Override
    public List<LevelEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public LevelEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public LevelEntity save(LevelEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
