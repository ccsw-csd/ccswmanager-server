package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;

/**
 * TODO asolerpa This type ...
 *
 */

@RequestMapping(value = "/person/")
@RestController
public class PersonController {

  @Autowired
  private PersonService personService;

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public List<PersonDto> findPersons() {

    return this.personService.findPersons();

  }

}
