package com.ccsw.ccswmanager.technology;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.technology.model.TechnologyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TechnologyDto save(@RequestBody TechnologyDto technologyDto) throws AlreadyExistsException {

        return this.beanMapper.map(this.service.save(technologyDto), TechnologyDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws ConflictOnDeletionException {

        this.service.deleteById(id);

    }

}
