package com.ccsw.ccswmanager.cost;

import com.ccsw.ccswmanager.common.exception.ConflictException;
import com.ccsw.ccswmanager.cost.model.CostCenterCostIndexDto;
import com.ccsw.ccswmanager.cost.model.PyramidGraphCustomerDto;
import com.ccsw.ccswmanager.cost.model.PyramidGraphDto;

import java.util.List;

public interface CostService {

    List<CostCenterCostIndexDto> getCostIndex();

	List<PyramidGraphDto> getPyramidGraph() throws ConflictException;

	List<PyramidGraphCustomerDto> getPyramidGraphCustomer() throws ConflictException;
}
