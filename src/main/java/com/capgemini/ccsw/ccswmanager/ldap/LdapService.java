package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPersonDto;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapService {

    Boolean check();

    List<LdapPersonDto> compareLdapToPersons();

    List<LdapPersonDto> comparePersonsToLdap();

    List<LdapPersonDto> compareLdapToPersonsScholars();

    List<LdapPersonDto> comparePersonsToLdapScholars();

    List<String> findUsernames(boolean contract);

}
