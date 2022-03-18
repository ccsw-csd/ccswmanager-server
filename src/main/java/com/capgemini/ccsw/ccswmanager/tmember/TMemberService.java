package com.capgemini.ccsw.ccswmanager.tmember;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.tmember.model.TMemberEntity;

public interface TMemberService {

    /**
     * @return
     */
    public List<TMemberEntity> findScholars();

    public List<TMemberEntity> findContracts();

    List<TMemberEntity> findAll();
}
