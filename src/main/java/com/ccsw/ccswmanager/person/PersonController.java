package com.ccsw.ccswmanager.person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonSimpleDto;

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

    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<PersonDto> saveOrUpdatePersons(@RequestBody List<PersonDto> persons) {

        return this.personService.saveOrUpdatePersons(persons);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {

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

    @RequestMapping(path = "/{idPerson}/photo", method = RequestMethod.GET)
    public @ResponseBody byte[] getPhoto(@PathVariable Long idPerson) throws IOException {

        InputStream in = getClass().getResourceAsStream("/userphoto.jpg");
        return IOUtils.toByteArray(in);

    }

}
