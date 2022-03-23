package com.capgemini.ccsw.ccswmanager.ldap.model;

public class LdapPersonDto {

    String name;

    String lastname;

    String username;

    public LdapPersonDto(String name, String lastname, String username) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
