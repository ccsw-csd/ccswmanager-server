package com.ccsw.ccswmanager.technology;

import com.ccsw.ccswmanager.technology.model.TechnologyEntity;

import java.util.List;


public interface TechnologyService {

    List<TechnologyEntity> findAll();

    TechnologyEntity getById(Long id);

    TechnologyEntity save(TechnologyEntity entity);

    void deleteById(Long id);

}
