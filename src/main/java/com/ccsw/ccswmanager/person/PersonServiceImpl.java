package com.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.center.CenterService;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.province.ProvinceService;
import com.ccsw.ccswmanager.scholar.ScholarService;
import com.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.ccsw.ccswmanager.tperson.TPersonService;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";
    private static final Integer ACTIVE_TRUE = 1;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    TPersonService tpersonService;

    @Autowired
    ScholarService scholarService;

    @Autowired
    CenterService centerService;

    @Autowired
    ProvinceService provinceService;

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

        List<TPersonEntity> persons = new ArrayList<>();
        List<PersonDto> personsToReturn = new ArrayList<>();
        List<TPersonEntity> personsLike = this.tpersonService.findAllTpersonsFromFilters(filter);
        List<PersonEntity> allPersons = this.personService.findAll();

        Set<String> personsToRemove = allPersons.stream().map(PersonEntity::getUsername).collect(Collectors.toSet());
        persons = personsLike.stream().filter(person -> !personsToRemove.contains(person.getUsername())).collect(Collectors.toList());

        personsToReturn = this.beanMapper.mapList(persons, PersonDto.class);

        PersonDto newPerson = new PersonDto();

        String nameLastname[] = filter.split(" ");
        StringBuilder lastname = new StringBuilder();
        for (int i = 1; i < nameLastname.length; i++) {
            lastname.append(nameLastname[i] + " ");
        }
        newPerson.setName(nameLastname[0]);
        newPerson.setLastname(EMPTY_STRING.equals(lastname.toString()) ? EMPTY_STRING : lastname.toString());

        personsToReturn.add(0, newPerson);

        return personsToReturn;
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
                BeanUtils.copyProperties(personTo, person, "id", "province");

                person.setCenter(this.centerService.getById(personTo.getCenter().getId()));
                if (personTo.getProvince() == null) {
                    person.setProvince(null);
                } else {
                    person.setProvince(this.provinceService.getById(personTo.getProvince().getId()));
                }

                this.personRepository.save(person);
            }
        });

        return findPersons();
    }

    @Override
    public List<PersonEntity> findScholars(String department, String grade, Integer active) {
        return this.personRepository.findByDepartmentAndGradeIsNullOrGradeIsAndActiveIsOrderByUsernameAsc(department, grade, active);
    }

    @Override
    public List<PersonEntity> findContracts(String department, String grade, Integer active) {
        return this.personRepository.findByDepartmentAndGradeIsNotNullAndGradeIsNotAndActiveIsOrderByUsernameAsc(department, grade, active);
    }

    @Override
    public List<PersonEntity> findAll() {
        return this.personRepository.findAllByOrderByUsernameAsc();
    }

    @Override
    public List<PersonEntity> findByDepartmentActives(String department) {
        return this.personRepository.findByDepartmentAndActive(department, ACTIVE_TRUE);
    }

    @Override
    public List<PersonEntity> findAllContractsActives() {
        return this.personRepository.findByGradeIsNotNullAndGradeIsNotAndActive(EMPTY_STRING, ACTIVE_TRUE);
    }

}
