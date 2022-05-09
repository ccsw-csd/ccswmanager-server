package com.ccsw.ccswmanager.tmember;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.tmember.model.TMemberEntity;

/**
 * @author dapalmie
 *
 */
public interface TMemberRepository extends CrudRepository<TMemberEntity, Long> {

    @EntityGraph(attributePaths = { "tperson" })
    List<TMemberEntity> findByIdGroupCnOrderByIdUserCnAsc(String groupCn);

}
