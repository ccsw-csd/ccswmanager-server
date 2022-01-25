package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */

@RequestMapping(value = "/person/")
@RestController
public class PersonController {

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  private PersonService personService;

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public List<PersonDto> findPersons() {

    return this.personService.findPersons();

  }

  /**
   * @param person
   * @return
   */
  @RequestMapping(path = "/", method = RequestMethod.POST)
  public PersonDto saveOrUpdatePerson(@RequestBody PersonDto person) {

    PersonEntity entity = this.personService.saveOrUpdatePerson(person);
    return this.beanMapper.map(entity, PersonDto.class);
  }

}
