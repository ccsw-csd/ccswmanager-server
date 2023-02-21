package com.ccsw.ccswmanager.center;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author asolerpa
 *
 */
@RequestMapping(value = "/center")
@RestController
public class CenterController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CenterService centerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<CenterDto> findCenters() {

        return this.beanMapper.mapList(this.centerService.findAll(), CenterDto.class);

    }

}
