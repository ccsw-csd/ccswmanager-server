package com.ccsw.ccswmanager.customer;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.customer.model.PersonCustomerWithPhotoEntity;

public interface PersonCustomerWithPhotoRepository extends CrudRepository<PersonCustomerWithPhotoEntity, Long> {

    @EntityGraph(attributePaths = { "person" })
    List<PersonCustomerWithPhotoEntity> findByCustomer(Long customerId);

}
