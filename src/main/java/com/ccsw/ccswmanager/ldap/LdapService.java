package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;

import java.util.List;

/**
 * @author dapalmie
 *
 */
public interface LdapService {

    Boolean check();

    List<LdapPersonDto> compareLdapToPersons(boolean contract);

    List<LdapPersonDto> comparePersonsToLdap(boolean contract);

    List<String> findUsernames(boolean contract);

    Boolean checkPersons();

    List<LdapPersonDto> compareLdapToPersons();

    List<LdapPersonDto> comparePersonsToLdap();

    List<String> findPersonUsernames();

    Boolean checkInterns();

    List<LdapPersonDto> compareLdapToInterns();

    List<LdapPersonDto> compareInternsToLdap();

    List<String> findInternUsernames();
}
