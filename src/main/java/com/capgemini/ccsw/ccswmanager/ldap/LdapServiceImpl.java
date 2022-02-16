package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO asolerpa This type ...
 *
 */
@Service
public class LdapServiceImpl implements LdapService {

  @Autowired
  LdapRepository ldapRepository;

  @Override
  public Boolean check() {

    List<String> persons = this.ldapRepository.comparePersonsToLdap();
    List<String> ldap = this.ldapRepository.compareLdapToPersons();

    if (persons.size() > 0 || ldap.size() > 0) {
      return false;
    } else {
      return true;
    }

  }

}
