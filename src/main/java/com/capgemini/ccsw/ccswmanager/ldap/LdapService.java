package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapService {

  Boolean check();

  List<LdapPerson> compareLdapToPersons(String tipoLista);

  List<LdapPerson> comparePersonsToLdap(String tipoLista);

  List<String> findUsernamesList(boolean grade);

}
