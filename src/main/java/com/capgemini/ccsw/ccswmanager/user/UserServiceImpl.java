package com.capgemini.ccsw.ccswmanager.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
