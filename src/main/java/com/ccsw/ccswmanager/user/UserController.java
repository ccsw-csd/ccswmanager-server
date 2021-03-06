package com.ccsw.ccswmanager.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.user.model.UserDto;

@RequestMapping(value = "/user/")
@RestController
public class UserController {

   @Autowired
   private UserService userService;
   
   @RequestMapping(path = "/", method = RequestMethod.GET)
   public List<UserDto> findAllUserPerson() {
      return this.userService.findAllUserPerson();
   }
   
   @RequestMapping(path = "/save", method = RequestMethod.PUT)
   public boolean saveUser(@RequestBody UserDto data){
	   return this.userService.saveUser(data);
   }
   
   @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
   public void deleteUser(@PathVariable("id") Long id){
	   this.userService.deleteUser(id);
   }
}
