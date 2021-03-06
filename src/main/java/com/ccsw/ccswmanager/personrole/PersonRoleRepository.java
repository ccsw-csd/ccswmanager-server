package com.ccsw.ccswmanager.personrole;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.personrole.model.PersonRoleEntity;

/**
 * @author dapalmie
 *
 */
public interface PersonRoleRepository extends CrudRepository<PersonRoleEntity, Long> {

    List<PersonRoleEntity> findAll();

}
