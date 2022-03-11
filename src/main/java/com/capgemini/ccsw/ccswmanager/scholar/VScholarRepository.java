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

    List<VScholarEntity> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);

    List<VScholarEntity> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDate, Date endDate);

    List<VScholarEntity> findAllByStartDateBetweenOrEndDateBetween(Date startDate, Date endDate, Date startDate2,
            Date endDate2);
}
