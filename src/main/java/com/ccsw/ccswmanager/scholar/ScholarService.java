package com.ccsw.ccswmanager.scholar;

import java.util.List;

import com.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.ccsw.ccswmanager.scholar.model.VScholarTimeLineDto;
import com.ccsw.ccswmanager.scholar.model.VScholarTimeLineSearchDto;

/**
 * @author jchengli
 *
 */
public interface ScholarService {

    ScholarEntity get(Long id);

    ScholarEntity getByPersonId(Long id);

    List<VScholarDto> findScholars();

    List<VScholarDto> saveOrUpdateScholars(List<VScholarDto> dto);

    List<VScholarTimeLineDto> findScholarsTimelineByDate(VScholarTimeLineSearchDto date);

    void deleteById(Long id);

}
