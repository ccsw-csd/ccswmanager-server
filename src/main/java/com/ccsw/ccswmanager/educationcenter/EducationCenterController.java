package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterDto;
import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.utils.ItemInUseException;

@RequestMapping(value = "/educationCenter")
@RestController
public class EducationCenterController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private EducationCenterService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<EducationCenterDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), EducationCenterDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public EducationCenterDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), EducationCenterDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public EducationCenterDto save(@RequestBody EducationCenterDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, EducationCenterEntity.class)), EducationCenterDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws ItemInUseException {

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            throw new ItemInUseException();
        }
    }

}