package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPersonDto;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapService {

    Boolean check();

    List<LdapPersonDto> compareLdapToPersons(boolean contract);

    List<LdapPersonDto> comparePersonsToLdap(boolean contract);

    List<String> findUsernames(boolean contract);

}
