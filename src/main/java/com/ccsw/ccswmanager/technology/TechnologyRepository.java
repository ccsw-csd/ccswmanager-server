package com.ccsw.ccswmanager.technology;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.technology.model.TechnologyEntity;

public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {

    @Override
    List<TechnologyEntity> findAll();

    TechnologyEntity getByName(String name);

}
