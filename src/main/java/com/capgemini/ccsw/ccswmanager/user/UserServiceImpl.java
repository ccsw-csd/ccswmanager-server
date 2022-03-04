package com.capgemini.ccsw.ccswmanager.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.ccswmanager.user.model.UserDto;
import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;

/**
 * @author pajimene
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserRepository userRepository;
   	
   /**
   * {@inheritDoc}
   */
   @Override
   public UserEntity getByUsername(String username) {
     return this.userRepository.getByUsername(username);
   }

   @Override
   public List<UserDto> findAllUserPerson() {
     List<UserEntity> entity = (List<UserEntity>) this.userRepository.findAll();
     List<UserDto> usersDto = new ArrayList<>();
	
     for(UserEntity user : entity)
       usersDto.add(this.transformEntity(user));
	   
     return usersDto;
   }

   @Transactional(readOnly = false)
   @Override
   public boolean saveUser(UserDto data) {
     UserEntity user = this.userRepository.getByUsername(data.getUsername());
     boolean exist = true;
		
     if(user == null && data.getId() == null){
       user = new UserEntity();
       exist = false;
       BeanUtils.copyProperties(data, user);
       this.userRepository.save(user);
     }
     else if(data.getId() != null)
     {
       if(user == null){
         exist = false;
         user = this.userRepository.findById(data.getId()).orElse(null);
         user.setUsername(data.getUsername());
         user.setRole(data.getRole());
         this.userRepository.save(user);
		}
       else if(data.getId() == user.getId()){
         exist = false;
         user.setRole(data.getRole());
         this.userRepository.save(user);
       }
     }

     return exist;
   }
	
   @Transactional(readOnly = false)
   @Override
   public void deleteUser(Long id) {
     this.userRepository.deleteById(id);
   }
   	
   UserDto transformEntity(UserEntity user){
     UserDto dto = new UserDto();
   		
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
