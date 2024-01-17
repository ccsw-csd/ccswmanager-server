package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.customer.model.*;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<CustomerEntity> findAll();

    CustomerEntity getById(Long id);

    CustomerEntity save(CustomerDto technologyDto) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;;

    List<CustomerEntity> findByUserRoles();

    Map<Long, Long> getPersonWithoutParentByCustomer();

    List<PersonCustomerEntity> findPersonCustomerOrganization(Long customerId);

    void savePersonCustomerOrganization(PersonCustomerEditDto request);

    List<OrganizationCustomerDto> findOrganizationChart(String customerIds);

	List<PersonCustomerEntity> findByParentId(Long parentId);
}
