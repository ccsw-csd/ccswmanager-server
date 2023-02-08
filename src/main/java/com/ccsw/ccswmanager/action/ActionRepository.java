package com.ccsw.ccswmanager.action;

import com.ccsw.ccswmanager.action.model.ActionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ActionRepository extends CrudRepository<ActionEntity, Long> {

    @Override
    List<ActionEntity> findAll();

}
