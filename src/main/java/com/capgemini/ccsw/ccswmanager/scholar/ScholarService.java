package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLine;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLineSearchDto;

/**
 * @author jchengli
 *
 */

public interface ScholarService {

    ScholarEntity get(long id);

    List<VScholarDto> findScholars();

    List<VScholarDto> saveOrUpdateScholars(List<VScholarDto> dto);

    List<VScholarTimeLine> findScholarsTimelineByDate(VScholarTimeLineSearchDto date);

    void deleteById(long id);

}
