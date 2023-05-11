package com.ccsw.ccswmanager.ldap.model;

import java.util.List;

public class ListsLdapPersonDto {

    List<LdapPersonDto> ldapToPersons;

    List<LdapPersonDto> personsToLdap;

    public ListsLdapPersonDto(List<LdapPersonDto> ldapToPersons, List<LdapPersonDto> personsToLdap) {
        this.ldapToPersons = ldapToPersons;
        this.personsToLdap = personsToLdap;
    }

    public List<LdapPersonDto> getLdapToPersons() {
        return ldapToPersons;
    }

    public void setLdapToPersons(List<LdapPersonDto> ldapToPersons) {
        this.ldapToPersons = ldapToPersons;
    }

    public List<LdapPersonDto> getPersonsToLdap() {
        return personsToLdap;
    }

    public void setPersonsToLdap(List<LdapPersonDto> personsToLdap) {
        this.personsToLdap = personsToLdap;
    }

}
