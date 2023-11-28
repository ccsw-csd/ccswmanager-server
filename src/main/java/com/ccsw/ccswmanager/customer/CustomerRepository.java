package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "managers"})
    List<CustomerEntity> findAll();

    CustomerEntity getByName(String name);

    List<CustomerEntity> findByManagersUsername(String username);
}
