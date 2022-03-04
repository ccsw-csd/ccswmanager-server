package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;

/**
 * TODO asolerpa This type ...
 *
 */
@Service
public class LdapServiceImpl implements LdapService {

  @Autowired
  LdapRepository ldapRepository;

  @Autowired
  private BeanMapper beanMapper;

  @Override
  public Boolean check() {

    List<LdapPerson> persons = this.ldapRepository.comparePersonsToLdap();
    List<LdapPerson> ldap = this.ldapRepository.compareLdapToPersons();
    
    List<LdapPerson> personsScholars = this.ldapRepository.comparePersonsToLdapScholars();
    List<LdapPerson> ldapScholars = this.ldapRepository.compareLdapToPersonsScholars();
     


    if (persons.size() > 0 || ldap.size() > 0 || personsScholars.size()>0 || ldapScholars.size() > 0) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public List<LdapPerson> compareLdapToPersons() {

    return this.ldapRepository.compareLdapToPersons();

  }

  @Override
  public List<LdapPerson> comparePersonsToLdap() {

    return this.ldapRepository.comparePersonsToLdap();

  }

  @Override
  public List<LdapPerson> compareLdapToPersonsScholars() {

    return this.ldapRepository.compareLdapToPersonsScholars();

  }

  @Override
  public List<LdapPerson> comparePersonsToLdapScholars() {

    return this.ldapRepository.comparePersonsToLdapScholars();

  }
  @Override
  public List<String> findUsernamesList(boolean contract) {

    return this.ldapRepository.findUsernamesList(contract);

  }

}
