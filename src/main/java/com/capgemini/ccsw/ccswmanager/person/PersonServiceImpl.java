package com.capgemini.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.center.CenterService;
import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.person.model.TPersonEntity;
import com.capgemini.ccsw.ccswmanager.scholar.ScholarService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  TPersonRepository tpersonRepository;

  @Autowired
  ScholarService scholarService;

  @Autowired
  CenterService centerService;

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
  public List<PersonDto> findByFilter(String filter) {

    TPersonEntity newPerson = new TPersonEntity();
    List<TPersonEntity> persons = new ArrayList<TPersonEntity>();
    String lastname = "";

    newPerson.setName(filter.split(" ")[0]);

    for (int i = 1; i < filter.split(" ").length; i++) {
      lastname = lastname + filter.split(" ")[i] + " ";
    }

    newPerson.setLastname(lastname);

    persons.add(newPerson);
    persons.addAll(this.tpersonRepository.findByFilter(filter));

    return this.beanMapper.mapList(persons, PersonDto.class);
  }

  @Override
  public List<PersonDto> saveOrUpdatePersons(List<PersonDto> personListTo) {

    personListTo.forEach(personTo -> {

      Objects.requireNonNull(personTo, "person");

      PersonEntity person = null;

      if (personTo.getId() != null) {
        person = get(personTo.getId());
      }

      if (person == null) {
        person = new PersonEntity();
      }

      if (personTo.getDelete() != null && personTo.getDelete() == true) {

        ScholarEntity scholarPerson = this.scholarService.get(personTo.getId());
        if (scholarPerson != null) {
          this.scholarService.deleteById(scholarPerson.getId());
        }

        this.personRepository.deleteById(personTo.getId());

      } else {
        BeanUtils.copyProperties(personTo, person, "id", "center");

        person.setCenter(this.centerService.getById(personTo.getCenter().getId()));

        this.personRepository.save(person);
      }
    });

    return findPersons();
  }
}
