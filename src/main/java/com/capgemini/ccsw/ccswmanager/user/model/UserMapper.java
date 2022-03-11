package com.capgemini.ccsw.ccswmanager.user.model;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
  
  private UserDto dto;
  
  public UserDto userMapper(UserEntity user)
  {
    List<String> customer = new ArrayList<>();
    dto = new UserDto();
    
    dto.setId(user.getId());   
    dto.setUsername(user.getUsername());
       
    if(user.getPerson() != null){
      dto.setName(user.getPerson().getName());
      dto.setLastname(user.getPerson().getLastname());
    }
    
    if(user.getTeams() != null)
    {
      user.getTeams().forEach(item -> customer.add(item.getCustomer()));
      dto.setCustomers(customer);
    }
       
    dto.setRole(user.getRole());
       
    return dto;
  }
}
