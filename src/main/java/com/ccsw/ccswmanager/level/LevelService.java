package com.ccsw.ccswmanager.level;

import com.ccsw.ccswmanager.level.model.LevelEntity;

import java.util.List;


public interface LevelService {

    List<LevelEntity> findAll();

    LevelEntity getById(Long id);

    LevelEntity save(LevelEntity entity);

    void deleteById(Long id);

}
