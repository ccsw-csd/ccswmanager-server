package com.ccsw.ccswmanager.person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonService {

    List<PersonDto> findPersons();

    Set<String> getAllUsernames();

    List<PersonDto> saveOrUpdatePersons(List<PersonDto> persons);

    Optional<PersonEntity> saveOrUpdatePerson(PersonDto personTo);

    PersonEntity get(Long id);

    List<PersonDto> findByFilter(String filter);

    List<PersonDto> findByFilterWithoutGrade(String filter);

    List<PersonEntity> findScholars(String department, String grade, Integer active);

    List<PersonEntity> findContracts(String department, String grade, Integer active);

    List<PersonEntity> findAll();

    List<PersonEntity> findByDepartmentActives(String department);

    List<PersonEntity> findAllContractsActives();

    void deleteById(Long id);

    PersonEntity save(PersonDto dto);

    List<PersonEntity> saveAll(List<PersonEntity> entities);

    PersonEntity getById(Long id);

}
