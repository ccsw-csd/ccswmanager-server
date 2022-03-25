package com.capgemini.ccsw.ccswmanager.personRole;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.personRole.model.PersonRoleEntity;

/**
 * @author dapalmie
 *
 */
public interface PersonRoleRepository extends CrudRepository<PersonRoleEntity, Long> {

    List<PersonRoleEntity> findAll();

}
