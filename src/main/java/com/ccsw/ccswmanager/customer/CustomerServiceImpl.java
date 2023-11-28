package com.ccsw.ccswmanager.customer;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.config.security.UserInfoDto;
import com.ccsw.ccswmanager.config.security.UserUtils;
import com.ccsw.ccswmanager.customer.model.CustomerDto;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import com.ccsw.ccswmanager.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("${app.code}")
    private String appCode;

    @Autowired
    CustomerRepository repository;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    PersonService personService;

    @Override
    public List<CustomerEntity> findAll() {

        UserInfoDto user = UserUtils.getUserDetails();

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

        if(user.getAppRoles(appCode).contains("MAINTENANCE")){
            return repository.findAll();
        } else{
            return repository.findByManagersUsername(user.getUsername());
        }
    }

}
