package com.ccsw.ccswmanager.ldap;

import java.util.List;

import com.ccsw.ccswmanager.ldap.model.ListsLdapPersonDto;

/**
 * @author dapalmie
 *
 */
public interface LdapService {

    Boolean checkPersons();

    List<String> findPersonUsernames();

    Boolean checkInterns();

    List<String> findInternUsernames();

    ListsLdapPersonDto compareLdapToPersons_PersonsToLdap();

    ListsLdapPersonDto compareLdapToInterns_InternsToLdap();
}
