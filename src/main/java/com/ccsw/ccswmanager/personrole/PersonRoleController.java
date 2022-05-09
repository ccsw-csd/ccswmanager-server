package com.ccsw.ccswmanager.personrole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.personrole.model.PersonRoleDto;

/**
 * @author dapalmie
 *
 */

@RequestMapping(value = "/person-roles/")
@RestController
public class PersonRoleController {

    @Autowired
    private PersonRoleService personRoleService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<PersonRoleDto> findPersonRoles() {

        return this.personRoleService.findRoles();
    }

}
