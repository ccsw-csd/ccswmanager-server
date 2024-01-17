package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dapalmie
 *
 */
@RequestMapping(value = "/person")
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

    @RequestMapping(path = "/filter/{filter}", method = RequestMethod.GET)
    public List<PersonDto> findByFilter(@PathVariable String filter) {

        return this.personService.findByFilter(filter);
    }

    @RequestMapping(path = "/scholar/{filter}", method = RequestMethod.GET)
    public List<PersonDto> findByFilterWithoutGrade(@PathVariable String filter) {

        return this.personService.findByFilterWithoutGrade(filter);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws ConflictOnDeletionException {

        this.personService.deleteById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PersonDto getById(@PathVariable Long id) {

        return this.beanMapper.map(this.personService.getById(id), PersonDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public PersonDto save(@RequestBody PersonDto personDto) throws AlreadyExistsException {

        return this.beanMapper.map(this.personService.save(personDto), PersonDto.class);
    }

    @RequestMapping(path = "/filter/person/{filter}", method = RequestMethod.GET)
    public List<PersonSimpleDto> findPersonByFilter(@PathVariable String filter) {

        return this.personService.findPersonByFilter(filter);
    }

    @RequestMapping(path = "/secured", method = RequestMethod.GET)
    public List<PersonDto> findByUserRoles() {

        return this.personService.findByUserRoles();
    }

}
