package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.customer.model.CustomerDto;
import com.ccsw.ccswmanager.customer.model.OrganizationCustomerDto;
import com.ccsw.ccswmanager.customer.model.PersonCustomerDto;
import com.ccsw.ccswmanager.customer.model.PersonCustomerEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/customer")
@RestController
public class CustomerController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CustomerService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        return this.beanMapper.mapList(this.service.findAll(), CustomerDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CustomerDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.service.getById(id), CustomerDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public CustomerDto save(@RequestBody CustomerDto customerDto) throws AlreadyExistsException {

        return this.beanMapper.map(this.service.save(customerDto), CustomerDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws ConflictOnDeletionException {

        this.service.deleteById(id);
    }

    @RequestMapping(path = "/secured", method = RequestMethod.GET)
    public List<CustomerDto> findByUserRoles() {

        return this.beanMapper.mapList(this.service.findByUserRoles(), CustomerDto.class);
    }

    @RequestMapping(path = "/person-without-parent", method = RequestMethod.GET)
    public Map<Long, Long> getPersonWithoutParentByCustomer() {

        return this.service.getPersonWithoutParentByCustomer();
    }

    @RequestMapping(path = "/{id}/organization-edit", method = RequestMethod.GET)
    public List<PersonCustomerDto> findPersonCustomerOrganization(@PathVariable(name = "id") Long customerId) {

        return this.beanMapper.mapList(this.service.findPersonCustomerOrganization(customerId), PersonCustomerDto.class);
    }

    @RequestMapping(path = "/organization-edit", method = RequestMethod.POST)
    public void savePersonCustomerOrganization(@RequestBody PersonCustomerEditDto request) {

        this.service.savePersonCustomerOrganization(request);
    }

    @RequestMapping(path = "/organization-chart", method = RequestMethod.GET)
    public List<OrganizationCustomerDto> findOrganizationChart(@RequestParam(name = "ids") String ids) {

        return this.service.findOrganizationChart(ids);
    }

}
