package com.ccsw.ccswmanager.ldap;

import java.util.List;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;
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

    ListsLdapPersonDto compareLdapToPersonsToLdap();

    ListsLdapPersonDto compareLdapToInternsToLdap();

    List<LdapPersonDto> compareLdapToPersons();

    List<LdapPersonDto> comparePersonsToLdap();

    List<LdapPersonDto> compareLdapToInterns();

    List<LdapPersonDto> compareInternsToLdap();
}
