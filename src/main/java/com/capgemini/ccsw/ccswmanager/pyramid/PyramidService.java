package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidIndexCostDto;

/**
 * @author jchengli
 *
 */

public interface PyramidService {

    List<PyramidIndexCostDto> returnPyramidIndexCost();

    List<PyramidIndexCostDto> saveOrUpdatePyramidCosts(List<PyramidIndexCostDto> dto);
}
