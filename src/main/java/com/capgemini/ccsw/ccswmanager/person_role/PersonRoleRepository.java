package com.capgemini.ccsw.ccswmanager.person_role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.person_role.model.PersonRoleEntity;

/**
 * @author dapalmie
 *
 */
public interface PersonRoleRepository extends CrudRepository<PersonRoleEntity, Long> {

    List<PersonRoleEntity> findAll();

}
