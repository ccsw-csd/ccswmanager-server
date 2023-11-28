package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.person.model.PersonEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author aolmosca
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "center", "province", "customers" })
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

    @EntityGraph(attributePaths = { "center", "customers" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNotAndActive(String grade, Integer active);

    @EntityGraph(attributePaths = { "center", "customers" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNotAndActiveAndCustomersManagersUsername(String grade, Integer active, String username);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

    PersonEntity getByUsername(String username);

    Optional<PersonEntity> findByUsername(String username);

    PersonEntity getById(Long id);

    PersonEntity getByEmail(String email);

    Object existsByUsername(String username);

    List<PersonEntity> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String name, String lastname, String username);

    @EntityGraph(attributePaths = { "center", "province", "customers" })
    List<PersonEntity> findByCustomersManagersUsername(String username);

    boolean existsByCustomersId(Long customerId);

}
