package com.capgemini.ccsw.ccswmanager.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.teams.model.TeamEntity;
import com.capgemini.ccsw.ccswmanager.user.model.UserDto;
import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;
import com.capgemini.ccsw.ccswmanager.user.model.UserMapper;

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
  
  @Autowired
  BeanMapper bean;
  
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
     UserMapper userMapper = new UserMapper();
  
     for(UserEntity user : entity)
       usersDto.add(userMapper.userMapper(user));
      
     return usersDto;
   }

   @Transactional(readOnly = false)
   @Override
   public boolean saveUser(UserDto data) {
     UserEntity user = this.userRepository.getByUsername(data.getUsername());
     boolean exist = true;
		
     if(user == null && data.getId() == null){
       exist = false;
       this.saveNewUser(data);
     }
     else if(data.getId() != null)
     {
       if(user == null || (user != null && data.getId() == user.getId())){
         exist = false;
         this.modifyUser(data);
		}
     }

     return exist;
   }

   private void saveNewUser(UserDto data)
   {
     TeamEntity team;
     List<TeamEntity> teams;
     UserEntity user = new UserEntity();
     
     BeanUtils.copyProperties(data, user);
     if(data.getCustomers() != null)
     {
       teams = new ArrayList<>();
       for(String item : data.getCustomers())
       {
         team = new TeamEntity();
         team.setUser(user);
         team.setCustomer(item);
         teams.add(team);
       }
       user.setTeams(teams);
     }
     this.userRepository.save(user);
   }
   
   private void modifyUser(UserDto data)
   {
     UserEntity newUser = this.userRepository.getById(data.getId());
     List<TeamEntity> modifyEntity = new ArrayList<>();
     TeamEntity team;
     
     newUser.setUsername(data.getUsername());
     newUser.setRole(data.getRole());
     newUser.getTeams().clear();
     
     for(String item : data.getCustomers())
     {
       team = new TeamEntity();
       team.setUser(newUser);
       team.setCustomer(item);
       modifyEntity.add(team);
     }
     newUser.getTeams().addAll(modifyEntity);

     this.userRepository.save(newUser);
   }
   
   @Transactional(readOnly = false)
   @Override
   public void deleteUser(String username) {
     this.userRepository.deleteByUsername(username);
   }
}
