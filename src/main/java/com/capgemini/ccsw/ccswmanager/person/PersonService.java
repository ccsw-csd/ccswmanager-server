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
     * @return
     */
    List<PersonDto> findPersons();

    /**
     * @param person
     * @return
     */
    List<PersonDto> saveOrUpdatePersons(List<PersonDto> persons);

    /**
     * @param id
     * @return
     */
    PersonEntity get(long id);

    /**
     * @param filter
     * @return
     */
    List<PersonDto> findByFilter(String filter);

    List<PersonEntity> findScholars(String department, String grade, int active);

    List<PersonEntity> findContracts(String department, String grade, int active);

    List<PersonEntity> findAll();

}
