package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;

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

  @RequestMapping(path = "/ldap", method = RequestMethod.GET)
  public List<LdapPerson> compareLdapToPersons() {

    return this.ldapService.compareLdapToPersons();
  }

  @RequestMapping(path = "/persons", method = RequestMethod.GET)
  public List<LdapPerson> comparePersonsToLdap() {

    return this.ldapService.comparePersonsToLdap();
  }

  @RequestMapping(path = "/list", method = RequestMethod.GET)
  public List<String> findUsernamesList() {

    return this.ldapService.findUsernamesList();
  }

}
