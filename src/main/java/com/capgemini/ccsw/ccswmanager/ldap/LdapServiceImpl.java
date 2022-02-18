package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;

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

    List<String[]> persons = this.ldapRepository.comparePersonsToLdap();
    List<String[]> ldap = this.ldapRepository.compareLdapToPersons();

    if (persons.size() > 0 || ldap.size() > 0) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public List<String[]> compareLdapToPersons() {

    return this.ldapRepository.compareLdapToPersons();

  }

  @Override
  public List<String[]> comparePersonsToLdap() {

    return this.ldapRepository.comparePersonsToLdap();

  }

}
