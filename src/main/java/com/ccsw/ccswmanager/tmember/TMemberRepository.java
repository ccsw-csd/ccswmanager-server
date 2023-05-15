package com.ccsw.ccswmanager.tmember;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.ccswmanager.tmember.model.TMemberEntity;

/**
 * @author dapalmie
 *
 */
public interface TMemberRepository extends CrudRepository<TMemberEntity, Long> {

    /*
    @Query("SELECT DISTINCT m FROM TMemberEntity m JOIN FETCH m.tperson tp LEFT JOIN FETCH tp.centerTranscode ctc LEFT JOIN FETCH ctc.center c WHERE m.id.userCn = :userCn ORDER BY m.id.groupCn ASC")
    List<TMemberEntity> findByIdGroupCnOrderByIdUserCnAsc(@Param("userCn") String userCn);
    
    
    @EntityGraph(attributePaths = { "tperson", "tperson.centerTranscode", "tperson.centerTranscode.center" })
    List<TMemberEntity> findByIdGroupCnOrderByIdUserCnAsc(String groupCn);
    */
    @Query("SELECT m FROM TMemberEntity m JOIN FETCH m.tperson tp LEFT JOIN FETCH tp.centerTranscode ctc LEFT JOIN FETCH ctc.center c WHERE m.id.groupCn = :groupCn ORDER BY m.id.userCn ASC")
    @EntityGraph(attributePaths = { "tperson", "tperson.centerTranscode", "tperson.centerTranscode.center" })
    List<TMemberEntity> findByIdGroupCnOrderByIdUserCnAsc(@Param("groupCn") String groupCn);

}
