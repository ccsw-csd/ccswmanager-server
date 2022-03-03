package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapService {

  Boolean check();

  List<LdapPerson> compareLdapToPersons();

  List<LdapPerson> comparePersonsToLdap();
  
  List<LdapPerson> compareLdapToPersonsBecarios();

  List<LdapPerson> comparePersonsToLdapBecarios();

  List<String> findUsernamesList(boolean contrato);

}
