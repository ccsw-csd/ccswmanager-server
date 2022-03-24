package com.capgemini.ccsw.ccswmanager.person_role;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.person_role.model.PersonRoleDto;

public interface PersonRoleService {

    /**
     * @return
     */
    List<PersonRoleDto> findRoles();

}
