package com.ccsw.ccswmanager.cost;

import com.ccsw.ccswmanager.common.exception.ConflictException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.cost.model.CostCenterCostIndexDto;
import com.ccsw.ccswmanager.cost.model.PyramidGraphCustomerDto;
import com.ccsw.ccswmanager.cost.model.PyramidGraphDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/cost")
@RestController
public class CostController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CostService service;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public List<CostCenterCostIndexDto> getCostIndex() {

        return this.service.getCostIndex();
    }

    @RequestMapping(path = "/pyramid/global", method = RequestMethod.GET)
    public List<PyramidGraphDto> getPyramidGraph() throws ConflictException {

        return this.service.getPyramidGraph();
    }

    @RequestMapping(path = "/pyramid/customer", method = RequestMethod.GET)
    public List<PyramidGraphCustomerDto> getPyramidGraphCustomer() throws ConflictException {

        return this.service.getPyramidGraphCustomer();
    }


}
