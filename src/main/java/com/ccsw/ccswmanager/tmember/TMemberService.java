package com.ccsw.ccswmanager.tmember;

import java.util.List;

import com.ccsw.ccswmanager.tmember.model.TMemberEntity;

public interface TMemberService {

    /**
     * @return
     */

    public List<TMemberEntity> findTMembers(String groupCn);

}
