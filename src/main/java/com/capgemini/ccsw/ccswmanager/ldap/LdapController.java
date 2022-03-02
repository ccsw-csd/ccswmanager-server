package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @RequestMapping(path = "/ldap/{tipoLista}", method = RequestMethod.GET)
  public List<LdapPerson> compareLdapToPersons(@PathVariable String tipoLista) {
		  return this.ldapService.compareLdapToPersons(tipoLista);
  }

  @RequestMapping(path = "/persons/{tipoLista}", method = RequestMethod.GET)
  public List<LdapPerson> comparePersonsToLdap(@PathVariable String tipoLista) {
	  return this.ldapService.comparePersonsToLdap(tipoLista);
  }

  @RequestMapping(path = "/list/{grade}", method = RequestMethod.GET)
  public List<String> findUsernamesList(@PathVariable boolean grade) {

    return this.ldapService.findUsernamesList(grade);
  }

}
