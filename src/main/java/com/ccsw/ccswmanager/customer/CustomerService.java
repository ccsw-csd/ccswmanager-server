package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.customer.model.CustomerDto;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerEntity> findAll();

    CustomerEntity getById(Long id);

    CustomerEntity save(CustomerDto technologyDto) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;;

    List<CustomerEntity> findByUserRoles();

}
