package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;
import com.ccsw.ccswmanager.ldap.model.ListsLdapPersonDto;

import java.util.List;

/**
 * @author dapalmie
 *
 */
public interface LdapService {

    Boolean checkPersonsToSync();

    Boolean checkInternsToSync();

    List<String> findPersonUsernames();

    List<String> findInternUsernames();

    ListsLdapPersonDto comparePersons();

    ListsLdapPersonDto compareInterns();

    @Deprecated
    Boolean checkPersons();

    @Deprecated
    Boolean checkInterns();

    @Deprecated
    List<LdapPersonDto> compareLdapToPersons();

    @Deprecated
    List<LdapPersonDto> comparePersonsToLdap();

    @Deprecated
    List<LdapPersonDto> compareLdapToInterns();

    @Deprecated
    List<LdapPersonDto> compareInternsToLdap();
}
