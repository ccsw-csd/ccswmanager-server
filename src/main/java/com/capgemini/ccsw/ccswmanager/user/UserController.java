package com.capgemini.ccsw.ccswmanager.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.user.model.UserPerson;

@RequestMapping(value = "/user/")
@RestController
public class UserController {

   @Autowired
   private UserService userService;

   @RequestMapping(path = "/", method = RequestMethod.GET)
   public List<UserPerson> findAllUserPerson() {

      return this.userService.findAllUserPerson();

   }
}
