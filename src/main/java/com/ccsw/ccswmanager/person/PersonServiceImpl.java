package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.center.CenterService;
import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.config.security.UserInfoDto;
import com.ccsw.ccswmanager.config.security.UserUtils;
import com.ccsw.ccswmanager.customer.CustomerService;
import com.ccsw.ccswmanager.customer.PersonCustomerRepository;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import com.ccsw.ccswmanager.customer.model.PersonCustomerEntity;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.person.model.PersonSimpleDto;
import com.ccsw.ccswmanager.province.ProvinceService;
import com.ccsw.ccswmanager.tperson.TPersonService;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private static final String ALLOWED_ROLE = "MAINTENANCE";

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

        if (user.getAppRoles(appCode).contains(ALLOWED_ROLE)) {
            return this.personRepository.findByGradeIsNotNullAndGradeIsNotAndActive(EMPTY_STRING, ACTIVE_TRUE);
        } else {
            return this.personRepository.findByGradeIsNotNullAndGradeIsNotAndActiveAndPersonCustomersCustomerManagersUsername(EMPTY_STRING, ACTIVE_TRUE, user.getUsername());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) throws ConflictOnDeletionException {

        if(this.personCustomerRepository.existsByParentId(id)){
            throw new ConflictOnDeletionException("La persona esta asignada como responsable en el organigrama, cambia la asignación antes del borrado.");
        }

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

        PersonEntity person = this.beanMapper.map(dto, PersonEntity.class);
        person.addPersonToPersonCustomers();
        removeOrphanByParent(person);
        return this.personRepository.save(person);
    }

    private void removeOrphanByParent(PersonEntity person){

        List<PersonCustomerEntity> children = this.customerService.findByParentId(person.getId());

        List<Long> customersId = person.getCustomers().stream().map(CustomerEntity::getId).collect(Collectors.toList());

        children.stream().filter(e -> !customersId.contains(e.getCustomer().getId())).forEach(e -> e.setParent(null));
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

        if (user.getAppRoles(appCode).contains(ALLOWED_ROLE)) {
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
