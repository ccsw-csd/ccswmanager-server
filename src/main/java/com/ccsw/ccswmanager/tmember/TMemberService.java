package com.ccsw.ccswmanager.tmember;

import com.ccsw.ccswmanager.tmember.model.TMemberEntity;

import java.util.List;

public interface TMemberService {

    List<TMemberEntity> findTMembers(String groupCn);

}
