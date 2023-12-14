package com.ccsw.ccswmanager.person.model;

import java.io.Serializable;

/**
 * @author amirzoya
 *
 */
public class PersonSimpleDto implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String lastname;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
