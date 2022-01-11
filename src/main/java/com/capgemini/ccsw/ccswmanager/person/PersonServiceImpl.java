package com.capgemini.ccsw.ccswmanager.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

   @Autowired
   PersonRepository personRepository;

}
