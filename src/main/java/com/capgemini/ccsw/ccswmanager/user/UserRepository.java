package com.capgemini.ccsw.ccswmanager.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;
import com.capgemini.ccsw.ccswmanager.user.model.UserPerson;

/**
 * @author pajimene
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

   /**
   * Recupera un usuario con su username
   * @param username
   * @return
   * @throws Exception
   */
	UserEntity getByUsername(String username);
}
