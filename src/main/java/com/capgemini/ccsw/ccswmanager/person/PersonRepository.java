package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

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
    List<PersonEntity> findByDepartmentAndGradeIsNullOrGradeIsAndActiveIsOrderByUsernameAsc(String department, String grade, int active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByDepartmentAndGradeIsNotNullAndGradeIsNotAndActiveIsOrderByUsernameAsc(String department, String grade, int active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByDepartment(String department);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByActive(Integer active);

    @EntityGraph(attributePaths = { "center" })
    List<PersonEntity> findByGradeIsNotNullAndGradeIsNot(String grade);

}
