package com.capgemini.ccsw.ccswmanager.user;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;
import com.capgemini.ccsw.ccswmanager.user.model.UserPerson;

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
   List<UserPerson> findAllUserPerson();
}
