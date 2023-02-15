package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dapalmie
 *
 */
@RequestMapping(value = "/ldap")
@RestController
public class LdapController {

    @Autowired
    private LdapService ldapService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public boolean check() {

        return this.ldapService.check();
    }

    @RequestMapping(path = "/ldap/{contract}", method = RequestMethod.GET)
    public List<LdapPersonDto> compareLdapToPersons(@PathVariable boolean contract) {

        return this.ldapService.compareLdapToPersons(contract);
    }

    @RequestMapping(path = "/persons/{contract}", method = RequestMethod.GET)
    public List<LdapPersonDto> comparePersonsToLdap(@PathVariable boolean contract) {

        return this.ldapService.comparePersonsToLdap(contract);
    }

    @RequestMapping(path = "/list/{contract}", method = RequestMethod.GET)
    public List<String> findUsernamesList(@PathVariable boolean contract) {

        return this.ldapService.findUsernames(contract);
    }

    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public boolean checkPersons() {

        return this.ldapService.checkPersons();
    }

    @RequestMapping(path = "/person/compare/ldap", method = RequestMethod.GET)
    public List<LdapPersonDto> compareLdapToPersons() {

        return this.ldapService.compareLdapToPersons();
    }

    @RequestMapping(path = "/person/compare/person", method = RequestMethod.GET)
    public List<LdapPersonDto> comparePersonsToLdap() {

        return this.ldapService.comparePersonsToLdap();
    }

    @RequestMapping(path = "/person/list", method = RequestMethod.GET)
    public List<String> findPersonUsernamesList() {

        return this.ldapService.findPersonUsernames();
    }

    @RequestMapping(path = "/intern", method = RequestMethod.GET)
    public boolean checkInterns() {

        return this.ldapService.checkInterns();
    }

    @RequestMapping(path = "/intern/compare/ldap", method = RequestMethod.GET)
    public List<LdapPersonDto> compareLdapToInterns() {

        return this.ldapService.compareLdapToInterns();
    }

    @RequestMapping(path = "/intern/compare/intern", method = RequestMethod.GET)
    public List<LdapPersonDto> compareInternsToLdap() {

        return this.ldapService.compareInternsToLdap();
    }

    @RequestMapping(path = "/intern/list", method = RequestMethod.GET)
    public List<String> findInternUsernamesList() {

        return this.ldapService.findInternUsernames();
    }

}
