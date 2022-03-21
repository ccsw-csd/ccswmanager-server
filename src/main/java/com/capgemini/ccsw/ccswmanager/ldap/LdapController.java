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

}
