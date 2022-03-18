package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPersonDto;

/**
 * TODO asolerpa This type ...
 *
 */

@RequestMapping(value = "/ldap/")
@RestController
public class LdapController {

    @Autowired
    private LdapService ldapService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public boolean check() {

        return this.ldapService.check();
    }

    @RequestMapping(path = "/ldap/", method = RequestMethod.GET)
    public List<LdapPersonDto> compareLdapToPersons() {
        return this.ldapService.compareLdapToPersons();
    }

    @RequestMapping(path = "/persons/", method = RequestMethod.GET)
    public List<LdapPersonDto> comparePersonsToLdapBecarios() {
        return this.ldapService.comparePersonsToLdap();
    }

    @RequestMapping(path = "/ldapScholars/", method = RequestMethod.GET)
    public List<LdapPersonDto> compareLdapToPersonsBecarios() {
        return this.ldapService.compareLdapToPersonsScholars();
    }

    @RequestMapping(path = "/personsScholars/", method = RequestMethod.GET)
    public List<LdapPersonDto> comparePersonsToLdap() {
        return this.ldapService.comparePersonsToLdapScholars();
    }

    @RequestMapping(path = "/list/{contract}", method = RequestMethod.GET)
    public List<String> findUsernamesList(@PathVariable boolean contract) {

        return this.ldapService.findUsernames(contract);
    }

}
