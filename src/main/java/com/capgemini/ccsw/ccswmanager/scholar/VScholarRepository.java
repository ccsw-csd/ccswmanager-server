package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;

/**
 * @author jchengli
 *
 */

public interface VScholarRepository extends CrudRepository<VScholarEntity, Long> {

    @Override
    List<VScholarEntity> findAll();

    List<VScholarEntity> findByStartDateGreaterThanEqualAndEndDateLessThanEqualOrStartDateLessThanEqualAndEndDateGreaterThanEqualOrStartDateBetweenOrEndDateBetweenOrderByStartDateAsc(
            Date startDateGrThEq, Date endDateLsThEq, Date startDateLsThEq, Date endDateGrThEq, Date startDateStartBtw,
            Date endDateStartBtw, Date startDateEndBtw, Date endDateEndBtw);

}
