package com.ccsw.ccswmanager.technology;

import com.ccsw.ccswmanager.technology.model.TechnologyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {

    @Override
    List<TechnologyEntity> findAll();

}
