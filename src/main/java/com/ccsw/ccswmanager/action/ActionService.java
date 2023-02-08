package com.ccsw.ccswmanager.action;

import com.ccsw.ccswmanager.action.model.ActionEntity;

import java.util.List;


public interface ActionService {

    List<ActionEntity> findAll();

    ActionEntity getById(Long id);

    ActionEntity save(ActionEntity entity);

    void deleteById(Long id);

}
