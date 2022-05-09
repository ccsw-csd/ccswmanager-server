package com.ccsw.ccswmanager.tmember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.tmember.model.TMemberEntity;

@Service
public class TMemberServiceImpl implements TMemberService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    TMemberRepository tmemberRepository;

    public List<TMemberEntity> findTMembers(String groupCn) {
        return this.tmemberRepository.findByIdGroupCnOrderByIdUserCnAsc(groupCn);
    }

}
