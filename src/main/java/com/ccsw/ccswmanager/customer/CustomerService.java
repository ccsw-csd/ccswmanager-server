package com.ccsw.ccswmanager.customer;

import java.util.List;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.customer.model.CustomerDto;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import com.ccsw.ccswmanager.customer.model.OrganizationCustomerDto;
import com.ccsw.ccswmanager.customer.model.PersonCustomerEditRequest;
import com.ccsw.ccswmanager.customer.model.PersonCustomerEntity;

public interface CustomerService {

    List<CustomerEntity> findAll();

    CustomerEntity getById(Long id);

    CustomerEntity save(CustomerDto technologyDto) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;;

    List<CustomerEntity> findByUserRoles();

    List<PersonCustomerEntity> findPersonCustomerOrganization(Long customerId);

    void savePersonCustomerOrganization(PersonCustomerEditRequest request);

    List<OrganizationCustomerDto> findOrganizationChart(String customerIds);

}
