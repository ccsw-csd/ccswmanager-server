package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    List<PersonEntity> findByDepartmentActives(String department);

    List<PersonEntity> findAllContractsActives();

    List<PersonEntity> findAllContractsActivesByUserRoles();

    void deleteById(Long id);

    PersonEntity save(PersonDto dto) throws AlreadyExistsException;

    PersonEntity getById(Long id);

    List<PersonEntity> findAll();

    List<PersonSimpleDto> findPersonByFilter(String filter);

    List<PersonDto> findByUserRoles();

    boolean existsByCustomersId(Long customerId);

}
