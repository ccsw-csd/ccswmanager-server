package com.capgemini.ccsw.ccswmanager.tperson;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface TPersonRepository extends CrudRepository<TPersonEntity, Long> {

    @EntityGraph(attributePaths = { "center" })
    List<TPersonEntity> findFirst15ByNameContainingOrLastnameContainingOrUsernameContaining(String filter1,
            String filter2, String filter3);

    @EntityGraph(attributePaths = { "center" })
    List<TPersonEntity> findAll();

}
