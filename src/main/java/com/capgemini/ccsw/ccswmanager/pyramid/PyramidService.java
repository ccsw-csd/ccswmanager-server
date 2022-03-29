package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.List;
import java.util.Map;

/**
 * @author jchengli
 *
 */

public interface PyramidService {

    List<Map<String, Double>> getPyramidIndexCost();

    List<Map<String, Double>> saveOrUpdatePyramidCosts(List<Map<String, Double>> gradeIndexCostMapList);
}
