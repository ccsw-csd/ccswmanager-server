package com.capgemini.ccsw.ccswmanager.tmember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.tmember.model.TMemberEntity;

@Service
public class TMemberServiceImpl implements TMemberService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    TMemberRepository tmemberRepository;

    @Override
    public List<TMemberEntity> findScholars() {
        return this.tmemberRepository.findByIdGroupCn("dlesccsw.becarios");
    }

    @Override
    public List<TMemberEntity> findContracts() {
        return this.tmemberRepository.findByIdGroupCn("dlesccsw");
    }

    @Override
    public List<TMemberEntity> findAll() {
        return this.tmemberRepository.findByIdUserCn("aalamino");
    }

}
