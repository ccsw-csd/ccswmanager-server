package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapService {

  Boolean check();

  List<String[]> compareLdapToPersons();

  List<String[]> comparePersonsToLdap();

}
