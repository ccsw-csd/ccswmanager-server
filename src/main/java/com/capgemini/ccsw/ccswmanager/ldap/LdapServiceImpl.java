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

    if (persons.size() > 0 || ldap.size() > 0) {
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

}
