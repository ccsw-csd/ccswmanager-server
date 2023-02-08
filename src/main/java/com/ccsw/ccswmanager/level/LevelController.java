package com.ccsw.ccswmanager.level;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.level.model.LevelDto;
import com.ccsw.ccswmanager.level.model.LevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/level")
@RestController
public class LevelController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private LevelService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<LevelDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), LevelDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public LevelDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), LevelDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public LevelDto save(@RequestBody LevelDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, LevelEntity.class)), LevelDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {

        this.service.deleteById(id);
    }

}
