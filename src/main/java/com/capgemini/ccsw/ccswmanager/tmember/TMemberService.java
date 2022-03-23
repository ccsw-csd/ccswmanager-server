package com.capgemini.ccsw.ccswmanager.tmember;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.tmember.model.TMemberEntity;

public interface TMemberService {

    /**
     * @return
     */

    public List<TMemberEntity> findTMembers(String groupCn);

}
