package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;

import java.util.List;

/**
 * @author dapalmie
 *
 */
public interface LdapService {

    Boolean checkPersons();

    List<LdapPersonDto> compareLdapToPersons();

    List<LdapPersonDto> comparePersonsToLdap();

    List<String> findPersonUsernames();

    Boolean checkInterns();

    List<LdapPersonDto> compareLdapToInterns();

    List<LdapPersonDto> compareInternsToLdap();

    List<String> findInternUsernames();
}
