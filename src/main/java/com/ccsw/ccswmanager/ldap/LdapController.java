package com.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.ldap.model.ListsLdapPersonDto;

/**
 * @author dapalmie
 *
 */
@RequestMapping(value = "/ldap")
@RestController
public class LdapController {

    @Autowired
    private LdapService ldapService;

    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public boolean checkPersons() {

        return this.ldapService.checkPersons();
    }

    @RequestMapping(path = "/intern", method = RequestMethod.GET)
    public boolean checkInterns() {

        return this.ldapService.checkInterns();
    }

    @RequestMapping(path = "/person/compare/personLdap", method = RequestMethod.GET)
    public ListsLdapPersonDto compareLdapToPersons_PersonsToLdap() {

        return this.ldapService.compareLdapToPersons_PersonsToLdap();

    }

    @RequestMapping(path = "/person/compare/internLdap", method = RequestMethod.GET)
    public ListsLdapPersonDto compareLdapToInterns_InternsToLdap() {

        return this.ldapService.compareLdapToInterns_InternsToLdap();

    }

    @RequestMapping(path = "/person/list", method = RequestMethod.GET)
    public List<String> findPersonUsernamesList() {

        return this.ldapService.findPersonUsernames();
    }

    @RequestMapping(path = "/intern/list", method = RequestMethod.GET)
    public List<String> findInternUsernamesList() {

        return this.ldapService.findInternUsernames();
    }

}
