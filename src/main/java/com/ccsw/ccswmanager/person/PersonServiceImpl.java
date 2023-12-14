package com.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.ccswmanager.center.CenterService;
import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.config.security.UserInfoDto;
import com.ccsw.ccswmanager.config.security.UserUtils;
import com.ccsw.ccswmanager.customer.CustomerService;
import com.ccsw.ccswmanager.customer.PersonCustomerRepository;
import com.ccsw.ccswmanager.customer.model.PersonCustomerEntity;
import com.ccsw.ccswmanager.customer.model.PersonCustomerSimpleDto;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.person.model.PersonSimpleDto;
import com.ccsw.ccswmanager.province.ProvinceService;
import com.ccsw.ccswmanager.tperson.TPersonService;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";

    private static final Integer ACTIVE_TRUE = 1;

    @Value("${app.code}")
    private String appCode;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonCustomerRepository personCustomerRepository;

    @Autowired
    CustomerService customerService;

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
    public List<PersonEntity> findAllContractsActivesByUserRoles() {

        UserInfoDto user = UserUtils.getUserDetails();

        if (user.getAppRoles(appCode).contains("MAINTENANCE")) {
            return this.personRepository.findByGradeIsNotNullAndGradeIsNotAndActive(EMPTY_STRING, ACTIVE_TRUE);
        } else {
            return this.personRepository.findByGradeIsNotNullAndGradeIsNotAndActiveAndPersonCustomersCustomerManagersUsername(EMPTY_STRING, ACTIVE_TRUE, user.getUsername());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {

        this.personRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
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

        List<PersonCustomerEntity> originalPersonCustomers = null;
        if (dto.getId() != null) {
            PersonEntity originalPerson = get(dto.getId());
            originalPersonCustomers = new ArrayList<>(originalPerson.getPersonCustomers());
        } else {
            originalPersonCustomers = new ArrayList<>();
        }

        PersonEntity personUpdated = this.beanMapper.map(dto, PersonEntity.class);
        personUpdated = this.personRepository.save(personUpdated);

        personUpdated.setPersonCustomers(createOrRemovePersonCustomers(originalPersonCustomers, personUpdated, dto));

        return personUpdated;
    }

    private List<PersonCustomerEntity> createOrRemovePersonCustomers(List<PersonCustomerEntity> personCustomers, PersonEntity updated, PersonDto dto) {

        //Check Customer deleted
        for (int i = personCustomers.size() - 1; i >= 0; i--) {

            PersonCustomerEntity personCustomerEntity = personCustomers.get(i);
            boolean contains = dto.getPersonCustomers().stream().anyMatch(x -> x.getId() != null && x.getId().equals(personCustomerEntity.getId()));

            if (contains == false) {
                personCustomerRepository.delete(personCustomerEntity);
                personCustomers.remove(i);
            }
        }

        //Check Customer new
        for (int i = 0; i < dto.getPersonCustomers().size(); i++) {

            PersonCustomerSimpleDto personCustomer = dto.getPersonCustomers().get(i);
            boolean contains = personCustomers.stream().anyMatch(x -> x.getId().equals(personCustomer.getId()));

            if (contains == false) {

                PersonCustomerEntity personCustomerEntity = new PersonCustomerEntity();
                personCustomerEntity.setPerson(updated);
                personCustomerEntity.setCustomer(customerService.getById(personCustomer.getCustomer().getId()));
                personCustomerRepository.save(personCustomerEntity);
                personCustomers.add(personCustomerEntity);
            }
        }

        return personCustomers;

    }

    @Override
    public PersonEntity getById(Long id) {

        return this.personRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonSimpleDto> findPersonByFilter(String filter) {

        List<PersonEntity> persons = this.personRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(filter, filter, filter);

        return this.beanMapper.mapList(persons, PersonSimpleDto.class);
    }

    @Override
    public List<PersonDto> findByUserRoles() {
        UserInfoDto user = UserUtils.getUserDetails();

        if (user.getAppRoles(appCode).contains("MAINTENANCE")) {
            return this.beanMapper.mapList(this.personRepository.findAll(), PersonDto.class);
        } else {
            return this.beanMapper.mapList(this.personRepository.findByPersonCustomersCustomerManagersUsername(user.getUsername()), PersonDto.class);
        }
    }

    @Override
    public boolean existsByCustomersId(Long customerId) {

        return this.personRepository.existsByPersonCustomersCustomerId(customerId);
    }

    @Override
    public PersonEntity findById(Long id) {

        if (id == null)
            return null;

        return personRepository.findById(id).orElse(null);
    }

}
