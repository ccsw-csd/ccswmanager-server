package com.ccsw.ccswmanager.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.technology.model.TechnologyDto;
import com.ccsw.ccswmanager.technology.model.TechnologyEntity;
import com.ccsw.ccswmanager.utils.ItemInUseException;

@RequestMapping(value = "/technology")
@RestController
public class TechnologyController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TechnologyService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<TechnologyDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), TechnologyDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public TechnologyDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), TechnologyDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public TechnologyDto save(@RequestBody TechnologyDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, TechnologyEntity.class)), TechnologyDto.class);
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
