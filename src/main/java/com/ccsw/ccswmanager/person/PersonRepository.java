package com.ccsw.ccswmanager.person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "center", "province", "personCustomers" })
    List<PersonEntity> findAll();

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findAllByOrderByUsernameAsc();

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByDepartmentAndGradeIsNullOrGradeIsAndActiveIsOrderByUsernameAsc(String department, String grade, Integer active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByDepartmentAndGradeIsNotNullAndGradeIsNotAndActiveIsOrderByUsernameAsc(String department, String grade, Integer active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByDepartmentAndActive(String department, Integer active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByActive(Integer active);

    @EntityGraph(attributePaths = { "center", "personCustomers" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNotAndActive(String grade, Integer active);

    @EntityGraph(attributePaths = { "center", "personCustomers" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNotAndActiveAndPersonCustomersCustomerManagersUsername(String grade, Integer active, String username);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

    PersonEntity getByUsername(String username);

    Optional<PersonEntity> findByUsername(String username);

    PersonEntity getById(Long id);

    PersonEntity getByEmail(String email);

    Object existsByUsername(String username);

    List<PersonEntity> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String name, String lastname, String username);

    @EntityGraph(attributePaths = { "center", "province", "personCustomers" })
    List<PersonEntity> findByPersonCustomersCustomerManagersUsername(String username);

    boolean existsByPersonCustomersCustomerId(Long customerId);

}
