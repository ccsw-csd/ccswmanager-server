package com.capgemini.ccsw.ccswmanager.scholar;

import java.sql.Date;
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

    List<VScholarEntity> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqualOrStartDateLessThanEqualAndEndDateGreaterThanEqualOrStartDateBetweenOrEndDateBetweenOrderByStartDateAsc(
            Date startDate, Date endDate, Date startDate1, Date endDate1, Date startDate2, Date endDate2,
            Date startDate3, Date endDate3);

}
