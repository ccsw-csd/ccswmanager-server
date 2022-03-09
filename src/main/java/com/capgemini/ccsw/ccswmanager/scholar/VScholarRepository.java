package com.capgemini.ccsw.ccswmanager.scholar;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;

/**
 * @author jchengli
 *
 */

public interface VScholarRepository extends CrudRepository<VScholarEntity, Long>{

  @Override
  List<VScholarEntity> findAll();
  
  @Query(value = "select * from v_scholar vs where"
  		+ "(vs.start_date <= :start_date and vs.end_date >= :start_date) or"
  		+ "(vs.start_date <= :end_date and vs.end_date >= :end_date)",
  		nativeQuery = true
  	)
  List<VScholarEntity> findByDate(@Param("start_date") Date startDate, @Param("end_date") Date endDate);
}
