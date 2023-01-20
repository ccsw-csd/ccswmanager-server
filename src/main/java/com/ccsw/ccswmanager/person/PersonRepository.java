package com.ccsw.ccswmanager.person;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Override
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

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNotAndActive(String grade, Integer active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

}
