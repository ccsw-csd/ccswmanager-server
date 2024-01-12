package com.ccsw.ccswmanager.costcenter;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.costcenter.model.CostCenterSimpleDto;
import com.ccsw.ccswmanager.costcenter.model.CostCenterDto;
import com.ccsw.ccswmanager.costcenter.model.CostCenterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/costCenter")
@RestController
public class CostCenterController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CostCenterService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<CostCenterDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), CostCenterDto.class);
    }

    @RequestMapping(path = "/simple/", method = RequestMethod.GET)
    public List<CostCenterSimpleDto> findAllSimple() {

        return this.beanMapper.mapList(this.service.findAll(), CostCenterSimpleDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CostCenterDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), CostCenterDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public CostCenterDto save(@RequestBody CostCenterDto dto) throws AlreadyExistsException {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, CostCenterEntity.class)), CostCenterDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws ConflictOnDeletionException {

        this.service.deleteById(id);
    }

}
