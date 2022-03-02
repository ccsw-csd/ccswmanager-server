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

   /**
   * Recupera los usuarios para el listado
   * @return
   */
   @Query(value = "select u.id, p.username, u.role, p.name, p.lastname from person p INNER JOIN user u ON p.username = u.username", nativeQuery = true)
   List<UserPerson> findAllUserPerson();
   
   @Query(value = "SELECT USERNAME FROM USER", nativeQuery = true)
   List<String> getAllUsername();
   
   @Query(value = "SELECT * FROM USER WHERE USERNAME = ?1", nativeQuery = true)
   UserEntity existUser(String username);
   
   
   
   
}
