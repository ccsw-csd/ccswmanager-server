package com.ccsw.ccswmanager.education;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.utils.ItemInUseException;

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

    @RequestMapping(path = { "", "{id}" }, method = RequestMethod.PUT)
    public EducationDto save(@PathVariable(name = "id", required = false) Long id, @RequestBody EducationDto educationDto) throws AlreadyExistsException {

        return this.beanMapper.map(this.service.save(id, educationDto), EducationDto.class);
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
