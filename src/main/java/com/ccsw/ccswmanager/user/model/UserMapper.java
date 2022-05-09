package com.ccsw.ccswmanager.user.model;

public class UserMapper {
  
  private UserDto dto;
  
  public UserDto userMapper(UserEntity user)
  {
    dto = new UserDto();
    
    dto.setId(user.getId());   
    dto.setUsername(user.getUsername());
       
    if(user.getPerson() != null){
      dto.setName(user.getPerson().getName());
      dto.setLastname(user.getPerson().getLastname());
    }
     
    dto.setRole(user.getRole());
       
    return dto;
  }
}
