package com.ccsw.ccswmanager.customer;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.customer.model.PersonCustomerEntity;

public interface PersonCustomerRepository extends CrudRepository<PersonCustomerEntity, Long> {

    @EntityGraph(attributePaths = { "person", "parent", "customer" })
    List<PersonCustomerEntity> findByCustomerIdAndPersonActive(Long customerId, Integer active);

}
