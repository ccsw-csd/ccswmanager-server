package com.ccsw.ccswmanager.action;

import com.ccsw.ccswmanager.action.model.ActionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository repository;

    @Override
    public List<ActionEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public ActionEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public ActionEntity save(ActionEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
