package com.capgemini.ccsw.ccswmanager.tperson;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author dapalmie,asolerpa
 *
 */
public interface TPersonRepository extends CrudRepository<TPersonEntity, Long> {

    @EntityGraph(attributePaths = { "center" })
    @Query("select t from TPersonEntity t where concat(name, ' ', lastname, ' ', username) LIKE %:filter%")
    List<TPersonEntity> findFirst15FromFilterOrderByUsernameAsc(String filter);

    @EntityGraph(attributePaths = { "center" })
    List<TPersonEntity> findAll();

}
