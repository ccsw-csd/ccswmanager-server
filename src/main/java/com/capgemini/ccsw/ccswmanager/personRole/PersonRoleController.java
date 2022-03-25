package com.capgemini.ccsw.ccswmanager.personRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.personRole.model.PersonRoleDto;

/**
 * @author dapalmie
 *
 */

@RequestMapping(value = "/personRoles/")
@RestController
public class PersonRoleController {

    @Autowired
    private PersonRoleService personRoleService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<PersonRoleDto> findPersonRoles() {

        return this.personRoleService.findRoles();
    }

}
