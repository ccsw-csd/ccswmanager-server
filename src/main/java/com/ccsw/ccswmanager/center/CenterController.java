package com.ccsw.ccswmanager.center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;

/**
 * @author asolerpa
 *
 */

@RequestMapping(value = "/center/")
@RestController
public class CenterController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CenterService centerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<CenterDto> findAllCenters() {

        return this.beanMapper.mapList(this.centerService.findAll(), CenterDto.class);

    }

}
