package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidIndexCostDto;

/**
 * @author jchengli
 *
 */

@RequestMapping(value = "/pyramid/")
@RestController
public class PyramidController {
    @Autowired
    private PyramidService pyramidService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<PyramidIndexCostDto> returnPyramidIndexCost() {
        return this.pyramidService.returnPyramidIndexCost();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public List<PyramidIndexCostDto> saveOrUpdatePyramidCosts(@RequestBody List<PyramidIndexCostDto> dto) {
        return this.pyramidService.saveOrUpdatePyramidCosts(dto);
    }
}
