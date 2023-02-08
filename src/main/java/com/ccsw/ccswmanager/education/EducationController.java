package com.ccsw.ccswmanager.education;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.education.model.EducationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/education")
@RestController
public class EducationController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private EducationService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<EducationDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), EducationDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public EducationDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), EducationDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public EducationDto save(@RequestBody EducationDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, EducationEntity.class)), EducationDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {

        this.service.deleteById(id);
    }

}
