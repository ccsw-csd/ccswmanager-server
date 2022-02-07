package com.capgemini.ccsw.ccswmanager.person;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.ccswmanager.person.model.TPersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface TPersonRepository extends CrudRepository<TPersonEntity, Long> {

  @Query(value = "select ct.center_id, persons.* from "
      + "(select * from t_person where concat(name, ' ', lastname, ' ', username) LIKE %:filter% and NOT EXISTS (select * from person where t_person.username = person.username) LIMIT 15) persons "
      + "INNER JOIN center_transcode ct ON persons.center = ct.name", nativeQuery = true)
  public List<TPersonEntity> findByFilter(@Param("filter") String filter);

}
