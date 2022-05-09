package com.ccsw.ccswmanager.personrole;

import java.util.List;

import com.ccsw.ccswmanager.personrole.model.PersonRoleDto;

public interface PersonRoleService {

    /**
     * @return
     */
    List<PersonRoleDto> findRoles();

}
