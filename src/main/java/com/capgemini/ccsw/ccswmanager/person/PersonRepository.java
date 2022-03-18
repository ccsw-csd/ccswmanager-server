package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Override
    List<PersonEntity> findAll();

    List<PersonEntity> findByDepartmentAndGradeIsNullOrGradeIsAndActiveIs(String department, String grade, int active);

    List<PersonEntity> findByDepartmentAndGradeIsNotNullAndGradeIsNotAndActiveIs(String department, String grade,
            int active);

}
