package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.HashMap;
import java.util.List;

/**
 * @author jchengli
 *
 */

public interface PyramidService {

    List<HashMap<String, Double>> getPyramidIndexCost();

    List<HashMap<String, Double>> saveOrUpdatePyramidCosts(List<HashMap<String, Double>> dto);
}
