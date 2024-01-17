package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.customer.CustomerService;
import com.ccsw.ccswmanager.customer.PersonCustomerRepository;
import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonCustomerRepository personCustomerRepository;

    @Mock
    private BeanMapper beanMapper;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private PersonServiceImpl personService;

    private static String USERNAME = "testusername";

    private static String EMAIL = "testemail@test.com";

    @Test
    public void saveExistsUsernameShouldThrowException() {

        PersonDto dto = new PersonDto();
        dto.setId(1L);
        dto.setUsername("testuser");
        dto.setEmail("testuser@test.com");
        PersonEntity existingPerson = new PersonEntity();
        existingPerson.setId(2L);
        existingPerson.setUsername("testuser");
        Mockito.when(personRepository.getByUsername("testuser")).thenReturn(existingPerson);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            personService.save(dto);
        });
    }

    @Test
    public void saveExistsEmailShouldThrowException() throws AlreadyExistsException {
        PersonDto dto = new PersonDto();
        dto.setId(1L);
        dto.setUsername("testuser");
        dto.setEmail("testuser@test.com");
        PersonEntity existingPerson = new PersonEntity();
        existingPerson.setId(2L);
        existingPerson.setUsername("testuser");
        Mockito.when(personRepository.getByUsername("testuser")).thenReturn(existingPerson);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            personService.save(dto);
        });
    }

    @Test
    public void deleteWithExistsIdShouldDeletePerson() throws ConflictOnDeletionException {

        Long idToDelete = 1L;

        when(personCustomerRepository.existsByParentId(idToDelete)).thenReturn(false);

        personService.deleteById(idToDelete);

        verify(personRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void saveWithNotExistsUsernameAndEmailShouldCreateNewPerson() throws AlreadyExistsException {
        PersonDto personDto = new PersonDto();
        personDto.setUsername("johndoe");
        personDto.setEmail("johndoe@example.com");

        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(1L);
        personEntity.setUsername("johndoe");
        personEntity.setEmail("johndoe@example.com");

        when(personRepository.getByUsername("johndoe")).thenReturn(null);
        when(personRepository.getByEmail("johndoe@example.com")).thenReturn(null);
        when(beanMapper.map(personDto, PersonEntity.class)).thenReturn(personEntity);
        when(personRepository.save(personEntity)).thenReturn(personEntity);

        when(customerService.findByParentId(any())).thenReturn(Collections.emptyList());

        PersonEntity savedPersonEntity = personService.save(personDto);

        verify(personRepository, times(1)).getByUsername("johndoe");
        verify(personRepository, times(1)).getByEmail("johndoe@example.com");
        verify(personRepository, times(1)).save(personEntity);

        verify(beanMapper, times(1)).map(personDto, PersonEntity.class);

        assertSame(personEntity, savedPersonEntity);
    }

}