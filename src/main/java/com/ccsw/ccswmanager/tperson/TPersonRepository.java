package com.ccsw.ccswmanager.tperson;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author dapalmie,asolerpa
 *
 */
public interface TPersonRepository extends CrudRepository<TPersonEntity, Long> {

    @EntityGraph(attributePaths = { "centerTranscode", "centerTranscode.center" })
    @Query("select t from TPersonEntity t where concat(name, ' ', lastname, ' ', username) LIKE %:filter% order by name, lastname asc")
    List<TPersonEntity> findTPersonsLikeFilter(String filter, Pageable pageable);

    @EntityGraph(attributePaths = { "centerTranscode", "centerTranscode.center" })
    @Query("select t from TPersonEntity t where concat(name, ' ', lastname, ' ', username) LIKE %:filter% AND grade = '' order by name, lastname asc")
    List<TPersonEntity> findTPersonsLikeFilterWithoutGarde(String filter, Pageable pageable);

    @EntityGraph(attributePaths = { "centerTranscode", "centerTranscode.center" })
    List<TPersonEntity> findAll();

    @EntityGraph(attributePaths = { "centerTranscode", "centerTranscode.center" })
    List<TPersonEntity> findByUsernameInOrSagaIn(List<String> username, List<String> saga);
}
