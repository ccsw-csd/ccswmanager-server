package com.ccsw.ccswmanager.level;

import com.ccsw.ccswmanager.level.model.LevelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface LevelRepository extends CrudRepository<LevelEntity, Long> {

    @Override
    List<LevelEntity> findAll();

}
