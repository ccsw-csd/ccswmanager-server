package com.capgemini.ccsw.ccswmanager.personrole;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.personrole.model.PersonRoleDto;

public interface PersonRoleService {

    /**
     * @return
     */
    List<PersonRoleDto> findRoles();

}
