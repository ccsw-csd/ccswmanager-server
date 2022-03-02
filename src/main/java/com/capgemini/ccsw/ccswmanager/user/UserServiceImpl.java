package com.capgemini.ccsw.ccswmanager.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.ccswmanager.user.model.UserDto;
import com.capgemini.ccsw.ccswmanager.user.model.UserEntity;
import com.capgemini.ccsw.ccswmanager.user.model.UserPerson;

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
   public List<UserPerson> findAllUserPerson() {
	   return userRepository.findAllUserPerson();
   }

   	@Transactional(readOnly = false)
	@Override
	public boolean saveUser(UserDto data) {
		
		UserEntity user = this.userRepository.getByUsername(data.getUsername());;
		boolean exist = true;
		if(user == null && data.getId() == null)
		{
			user = new UserEntity();
			exist = false;
			BeanUtils.copyProperties(data, user);
			this.userRepository.save(user);
		}
		else if(data.getId() == user.getId())
		{
			exist = false;
			user.setUsername(data.getUsername());
			user.setRole(data.getRole());
			this.userRepository.save(user);
		}
		
		return exist;
	}
	
   	@Transactional(readOnly = false)
	@Override
	public void deleteUSer(Long id) {
		this.userRepository.deleteById(id);
	}
   
}
