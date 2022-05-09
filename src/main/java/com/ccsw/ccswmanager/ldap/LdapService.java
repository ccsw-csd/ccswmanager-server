package com.ccsw.ccswmanager.ldap;

import java.util.List;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;

/**
 * @author dapalmie
 *
 */
public interface LdapService {

    Boolean check();

    List<LdapPersonDto> compareLdapToPersons(boolean contract);

    List<LdapPersonDto> comparePersonsToLdap(boolean contract);

    List<String> findUsernames(boolean contract);

}
