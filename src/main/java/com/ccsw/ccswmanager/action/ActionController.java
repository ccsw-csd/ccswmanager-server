package com.ccsw.ccswmanager.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.action.model.ActionDto;
import com.ccsw.ccswmanager.action.model.ActionEntity;
import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;

@RequestMapping(value = "/action")
@RestController
public class ActionController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ActionService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<ActionDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), ActionDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ActionDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), ActionDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ActionDto save(@RequestBody ActionDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, ActionEntity.class)), ActionDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws AlreadyExistsException {

        this.service.deleteById(id);

    }

}
