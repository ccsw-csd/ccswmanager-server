package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  private BeanMapper beanMapper;

  @Override
  public List<PersonDto> findPersons() {

    return this.beanMapper.mapList(this.personRepository.findAll(), PersonDto.class);
  }

  @Override
  public PersonEntity get(long id) {

    return this.personRepository.findById(id).orElse(null);
  }

  @Override
  public PersonEntity saveOrUpdatePerson(PersonDto personTo) {

    Objects.requireNonNull(personTo, "person");

    PersonEntity person = null;
    if (personTo.getId() != null)
      person = get(personTo.getId());

    if (person == null) {
      person = new PersonEntity();
    }

    BeanUtils.copyProperties(personTo, person, "id", "center");

    return this.personRepository.save(person);
  }

}
