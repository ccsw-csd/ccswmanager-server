package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.customer.model.PersonCustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonCustomerRepository extends CrudRepository<PersonCustomerEntity, Long> {

    @EntityGraph(attributePaths = { "person", "parent", "customer" })
    List<PersonCustomerEntity> findByPersonActive(Integer active);

    @EntityGraph(attributePaths = { "person", "parent", "customer" })
    List<PersonCustomerEntity> findByCustomerIdAndPersonActive(Long customerId, Integer active);

    boolean existsByParentId(Long parentId);

    @EntityGraph(attributePaths = { "person", "parent", "customer" })
    List<PersonCustomerEntity> findByParentId(Long parentId);

}
