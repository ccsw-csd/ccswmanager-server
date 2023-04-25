package com.ccsw.ccswmanager.person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.center.CenterService;
import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.config.security.UserUtils;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.province.ProvinceService;
import com.ccsw.ccswmanager.tperson.TPersonService;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";

    private static final Integer ACTIVE_TRUE = 1;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TPersonService tpersonService;

    @Autowired
    InternService internService;

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
    public PersonEntity get(Long id) {

        return this.personRepository.findById(id).orElse(null);
    }

    private PersonEntity getOrNew(Long id) {

        return this.personRepository.findById(id).orElse(new PersonEntity());
    }

    @Override
    public List<PersonDto> findByFilter(String filter) {

        List<TPersonEntity> persons = this.tpersonService.findAllTPersonsFromFilters(filter); //TODO rehacer query

        return getPerson(filter, persons, this.getAllUsernames());
    }

    @Override
    public List<PersonDto> findByFilterWithoutGrade(String filter) {

        List<TPersonEntity> persons = this.tpersonService.findAllTPersonsFromFiltersWithoutGrade(filter); //TODO rehacer query

        return getPerson(filter, persons, internService.getAllUsernames());
    }

    private List<PersonDto> getPerson(String filter, List<TPersonEntity> personsLike, Set<String> usernamesToRemove) {

        List<TPersonEntity> persons = personsLike.stream().filter(person -> !usernamesToRemove.contains(person.getUsername())).collect(Collectors.toList());

        List<PersonDto> personsToReturn = this.beanMapper.mapList(persons, PersonDto.class);

        addMockPerson(filter, personsToReturn);

        return personsToReturn;
    }

    private void addMockPerson(String filter, List<PersonDto> personsToReturn) {

        PersonDto newPerson = new PersonDto();

        String[] nameLastname = filter.split(SPACE_STRING);
        StringBuilder lastname = new StringBuilder();
        for (int i = 1; i < nameLastname.length; i++) {
            lastname.append(nameLastname[i]).append(SPACE_STRING);
        }
        newPerson.setName(nameLastname[0]);
        newPerson.setLastname(EMPTY_STRING.equals(lastname.toString()) ? EMPTY_STRING : lastname.toString());

        personsToReturn.add(0, newPerson);
    }

    @Override
    public Set<String> getAllUsernames() {

        return findAll().stream().map(PersonEntity::getUsername).collect(Collectors.toSet());
    }

    @Override
    public List<PersonDto> saveOrUpdatePersons(List<PersonDto> personListTo) {

        personListTo.forEach(personTo -> {
            Objects.requireNonNull(personTo, "person");

            saveOrUpdatePerson(personTo);
        });

        return findPersons();
    }

    @Override
    public Optional<PersonEntity> saveOrUpdatePerson(PersonDto personTo) {

        Optional<PersonEntity> existingPerson = personRepository.findByUsername(personTo.getUsername());

        if (existingPerson.isPresent() && !existingPerson.get().getId().equals(personTo.getId())) {
            throw new IllegalArgumentException("Ya existe una persona con el mismo nombre de usuario");
        }

        if (personTo.getId() != null && personTo.getDelete() != null && personTo.getDelete()) {
            this.personRepository.deleteById(personTo.getId());
            return Optional.empty();
        } else {
            PersonEntity person = personTo.getId() != null ? getOrNew(personTo.getId()) : new PersonEntity();

            BeanUtils.copyProperties(personTo, person, "id", "center", "province");

            person.setCenter(this.centerService.getById(personTo.getCenter().getId()));
            person.setProvince(personTo.getProvince() != null ? this.provinceService.getById(personTo.getProvince().getId()) : null);
            person.setUpdatedAt(LocalDateTime.now());
            person.setUpdatedBy(UserUtils.getUserDetails().getUsername());
            this.personRepository.save(person);

            return Optional.of(person);
        }
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

    @Override
    public void deleteById(Long id) {

        this.personRepository.deleteById(id);
    }

    @Override
    public PersonEntity save(PersonDto dto) throws AlreadyExistsException {

        if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
            PersonEntity personByUsername = this.personRepository.getByUsername(dto.getUsername());

            if (personByUsername != null && (dto.getId() == null || !personByUsername.getId().equals(dto.getId()))) {
                throw new AlreadyExistsException("El username ya está en uso");
            }
        }

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            PersonEntity personByEmail = this.personRepository.getByEmail(dto.getEmail());

            if (personByEmail != null && (dto.getId() == null || !personByEmail.getId().equals(dto.getId()))) {
                throw new AlreadyExistsException("El email ya está en uso");
            }
        }

        return this.personRepository.save(this.beanMapper.map(dto, PersonEntity.class));
    }

    @Override
    public List<PersonEntity> saveAll(List<PersonEntity> entities) {

        this.personRepository.saveAll(entities);

        return findAll();
    }

    @Override
    public PersonEntity getById(Long id) {

        return this.personRepository.findById(id).orElse(null);
    }

}
