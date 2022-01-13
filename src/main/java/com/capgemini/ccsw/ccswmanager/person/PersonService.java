package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;

/**
 * @author aolmosca
 *
 */
public interface PersonService {

  /**
   * @return
   */
  List<PersonDto> findPersons();

}
