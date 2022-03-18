package com.capgemini.ccsw.ccswmanager.tmember;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.tmember.model.TMemberEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface TMemberRepository extends CrudRepository<TMemberEntity, Long> {

    @EntityGraph(attributePaths = { "tperson" })
    List<TMemberEntity> findByIdGroupCn(String groupCn);

    List<TMemberEntity> findByIdUserCn(String user);

}