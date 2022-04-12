package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCountDto;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidDto;

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
    public List<Map<String, Double>> getPyramidIndexCost() {
        return this.pyramidService.getPyramidIndexCost();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public List<Map<String, Double>> saveOrUpdatePyramidCosts(
            @RequestBody List<Map<String, Double>> gradeIndexCostMapList) {
        return this.pyramidService.saveOrUpdatePyramidCosts(gradeIndexCostMapList);
    }

    @RequestMapping(path = "/profileCountIndexGraph", method = RequestMethod.GET)
    public List<PyramidDto> getPyramidsProfileCountIndex() {
        return this.pyramidService.getPyramidsProfileCountIndex();
    }

    @RequestMapping(path = "/profileCountGraph", method = RequestMethod.GET)
    public List<PyramidCountDto> getPyramidsProfileCount() {
        return this.pyramidService.getPyramidsProfileCount();
    }

}
