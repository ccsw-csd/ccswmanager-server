package com.capgemini.ccsw.ccswmanager.user;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.user.model.UserDto;
import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;

/**
 * @author pajimene
 *
 */
public interface UserService {

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
  List<UserDto> findAllUserPerson();
   
  /**
   * Guarda o actualiza un usuario.
   * Siempre y cuando el username sea
   * único. 
   * @param userDto 
   * @return
   */
  boolean saveUser(UserDto userDto);
   
  void deleteUser(Long id);
   
}
