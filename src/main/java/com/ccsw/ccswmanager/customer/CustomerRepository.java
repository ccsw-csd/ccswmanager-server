package com.ccsw.ccswmanager.customer;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.customer.model.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "managers" })
    List<CustomerEntity> findAll();

    CustomerEntity getByName(String name);

    List<CustomerEntity> findByManagersUsername(String username);

    List<CustomerEntity> findByIdIn(Collection<Long> ids);
}
