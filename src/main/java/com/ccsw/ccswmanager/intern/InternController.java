package com.ccsw.ccswmanager.intern;

import static java.lang.Boolean.FALSE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.intern.model.InternDto;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.intern.model.TimeLineDto;
import com.ccsw.ccswmanager.intern.model.TimeLineSearchDto;

@RequestMapping(value = "/intern")
@RestController
public class InternController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private InternService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<InternDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), InternDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public InternDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), InternDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public InternDto save(@RequestBody InternDto dto) {

        return this.beanMapper.map(this.service.save(this.beanMapper.map(dto, InternEntity.class)), InternDto.class);
    }

    @RequestMapping(path = "/predict/{number}", method = RequestMethod.POST)
    public void saveMassive(@RequestBody InternDto dto, @PathVariable Long quantity) {

        this.service.savePredict(this.beanMapper.map(dto, InternEntity.class), quantity);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {

        this.service.deleteById(id);
    }

    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<InternDto> bulk(@RequestBody List<InternDto> dtos) {

        List<InternDto> toDelete = dtos.stream().filter(InternDto::getDelete).collect(Collectors.toList());
        List<InternDto> toSave = dtos.stream().filter(e -> FALSE.equals(e.getDelete())).collect(Collectors.toList());

        this.service.deleteAll(this.beanMapper.mapList(toDelete, InternEntity.class));

        return this.beanMapper.mapList(this.service.saveAll(this.beanMapper.mapList(toSave, InternEntity.class)),
                InternDto.class);
    }

    @RequestMapping(path = "/dateFilter", method = RequestMethod.POST)
    public List<TimeLineDto> findTimelineByDate(@RequestBody TimeLineSearchDto date) {

        return this.service.findTimelineByDate(date);
    }

}
