package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;

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

}
