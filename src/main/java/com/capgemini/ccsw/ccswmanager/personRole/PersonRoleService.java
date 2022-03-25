package com.capgemini.ccsw.ccswmanager.personRole;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.personRole.model.PersonRoleDto;

public interface PersonRoleService {

    /**
     * @return
     */
    List<PersonRoleDto> findRoles();

}
