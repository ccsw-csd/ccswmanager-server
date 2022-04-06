package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.List;
import java.util.Map;

import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCountDto;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidDto;

/**
 * @author jchengli
 *
 */

public interface PyramidService {

    List<Map<String, Double>> getPyramidIndexCost();

    List<Map<String, Double>> saveOrUpdatePyramidCosts(List<Map<String, Double>> gradeIndexCostMapList);

    List<PyramidDto> getPyramidsProfileCountIndex();

    List<PyramidCountDto> getPyramidsProfileCount();
}
