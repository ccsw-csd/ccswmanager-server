package com.ccsw.ccswmanager.tmember;

import com.ccsw.ccswmanager.tmember.model.TMemberEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author dapalmie
 *
 */
public interface TMemberRepository extends CrudRepository<TMemberEntity, Long> {

    //TODO revisar, monta la query correctamente pero aun asi lanza sub consultas
    @EntityGraph(attributePaths = { "tperson", "tperson.centerTranscode", "tperson.centerTranscode.center" })
    List<TMemberEntity> findByIdGroupCnOrderByIdUserCnAsc(String groupCn);

}
