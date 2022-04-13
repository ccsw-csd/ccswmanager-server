package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
public interface PersonService {

    /**
     * @return {@link List} of {@link PersonDto}
     */
    List<PersonDto> findPersons();

    /**
     * @param persons
     * @return {@link List} of {@link PersonDto}
     */
    List<PersonDto> saveOrUpdatePersons(List<PersonDto> persons);

    /**
     * @param id
     * @return a {@link PersonDto}
     */
    PersonEntity get(long id);

    /**
     * @param filter
     * @return {@link List} of {@link PersonDto}
     */
    List<PersonDto> findByFilter(String filter);

    List<PersonEntity> findScholars(String department, String grade, Integer active);

    List<PersonEntity> findContracts(String department, String grade, Integer active);

    List<PersonEntity> findAll();

    List<PersonEntity> findByDepartmentActives(String department);

    List<PersonEntity> findAllContractsActives();

}
