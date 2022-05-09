package com.ccsw.ccswmanager.personrole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.personrole.model.PersonRoleDto;

@Service
public class PersonRoleServiceImpl implements PersonRoleService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    PersonRoleRepository personRoleRepository;

    @Override
    public List<PersonRoleDto> findRoles() {
        return this.beanMapper.mapList(this.personRoleRepository.findAll(), PersonRoleDto.class);
    }

}
