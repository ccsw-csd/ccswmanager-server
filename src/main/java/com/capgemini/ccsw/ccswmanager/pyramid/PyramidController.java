package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public List<HashMap<String, Double>> getPyramidIndexCost() {
        return this.pyramidService.getPyramidIndexCost();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public List<HashMap<String, Double>> saveOrUpdatePyramidCosts(@RequestBody List<HashMap<String, Double>> dto) {
        return this.pyramidService.saveOrUpdatePyramidCosts(dto);
    }
}
