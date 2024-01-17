package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.config.security.UserInfoDto;
import com.ccsw.ccswmanager.config.security.UserUtils;
import com.ccsw.ccswmanager.customer.model.*;
import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final int PERSON_ACTIVE = 1;
    private static final String ALLOWED_ROLE = "MAINTENANCE";

    @Value("${app.code}")
    private String appCode;

    @Autowired
    CustomerRepository repository;

    @Autowired
    PersonCustomerRepository personCustomerRepository;

    @Autowired
    PersonCustomerWithPhotoRepository personCustomerWithPhotoRepository;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    PersonService personService;

    @Override
    public List<CustomerEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public CustomerEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public CustomerEntity save(CustomerDto customerDto) throws AlreadyExistsException {

        CustomerEntity existsCustomer = this.repository.getByName(customerDto.getName());

        if (existsCustomer != null && (customerDto.getId() == null || !existsCustomer.getId().equals(customerDto.getId()))) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }

        return repository.save(this.beanMapper.map(customerDto, CustomerEntity.class));
    }

    @Override
    public void deleteById(Long id) throws ConflictOnDeletionException {

        boolean existsPerson = personService.existsByCustomersId(id);
        if (existsPerson) {
            throw new ConflictOnDeletionException("No se puede borrar el cliente porque está relacionada con una persona");
        }

        repository.deleteById(id);
    }

    @Override
    public List<CustomerEntity> findByUserRoles() {

        UserInfoDto user = UserUtils.getUserDetails();

        if (user.getAppRoles(appCode).contains(ALLOWED_ROLE)) {
            return repository.findAll();
        } else {
            return repository.findByManagersUsername(user.getUsername());
        }
    }

    @Override
    public Map<Long, Long> getPersonWithoutParentByCustomer() {

        return personCustomerRepository.findByPersonActive(PERSON_ACTIVE).stream().filter(e -> e.getParent() == null).collect(Collectors.groupingBy(e -> e.getCustomer().getId(), Collectors.counting()));
    }

    @Override
    public List<PersonCustomerEntity> findPersonCustomerOrganization(Long customerId) {

		return personCustomerRepository.findByCustomerIdAndPersonActive(customerId, PERSON_ACTIVE);
    }

    private boolean isDistinctPerson(PersonEntity personEntity, PersonDto personDto) {

        if (personEntity == null && personDto != null)
            return true;
        if (personEntity != null && personDto == null)
            return true;

        return !personEntity.getId().equals(personDto.getId());
    }

    @Override
    @Transactional
    public void savePersonCustomerOrganization(PersonCustomerEditDto request) {

        for (PersonCustomerDto personCustomerData : request.getData()) {

            PersonCustomerEntity personCustomer = personCustomerRepository.findById(personCustomerData.getId()).orElse(null);

            if (isDistinctPerson(personCustomer.getParent(), personCustomerData.getParent())) {

                PersonEntity newPerson = null;
                if (personCustomerData.getParent() != null)
                    newPerson = personService.findById(personCustomerData.getParent().getId());

                personCustomer.setParent(newPerson);
                personCustomerRepository.save(personCustomer);
            }
        }
    }

    @Override
    public List<OrganizationCustomerDto> findOrganizationChart(String customerIds) {

        List<OrganizationCustomerDto> list = new ArrayList<>();
        List<Long> longIds = Stream.of(customerIds.split(",")).map(Long::parseLong).collect(Collectors.toList());

        List<CustomerEntity> customers = repository.findByIdIn(longIds);

        for (CustomerEntity customer : customers) {

            OrganizationCustomerDto data = new OrganizationCustomerDto();

            data.setId(customer.getId());
            data.setName(customer.getName());

            List<PersonCustomerWithPhotoEntity> personList = personCustomerWithPhotoRepository.findByCustomerAndPersonActive(customer.getId(), PERSON_ACTIVE);
            List<PersonCustomerWithPhotoDto> memberList = new ArrayList<>();

            for (PersonCustomerWithPhotoEntity person : personList) {

                PersonCustomerWithPhotoDto member = beanMapper.map(person, PersonCustomerWithPhotoDto.class);
                byte[] photo = person.getPhoto();

                if (photo == null) {
                    try {
                        photo = IOUtils.toByteArray(getClass().getResourceAsStream("/userphoto.jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                member.setPhoto(Base64.getEncoder().encodeToString(photo));

                memberList.add(member);
            }
            data.setMembers(memberList);
            list.add(data);
        }
        return list;
    }

    @Override
    public List<PersonCustomerEntity> findByParentId(Long parentId) {

        return personCustomerRepository.findByParentId(parentId);
    }

}
