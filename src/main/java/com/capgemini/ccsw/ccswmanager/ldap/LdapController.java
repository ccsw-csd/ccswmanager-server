package com.capgemini.ccsw.ccswmanager.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
