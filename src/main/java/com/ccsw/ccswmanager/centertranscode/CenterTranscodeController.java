package com.ccsw.ccswmanager.centertranscode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.center.model.CenterDto;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;

@RequestMapping(value = "centerTranscode")
@RestController
public class CenterTranscodeController {
    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CenterTranscodeService centerTranscodeService;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public CenterDto findCenterByName(@PathVariable String name) {
        return this.beanMapper.map(this.centerTranscodeService.findByName(name), CenterDto.class);
    }

}
