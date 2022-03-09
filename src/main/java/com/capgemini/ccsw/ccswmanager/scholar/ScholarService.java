package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarSearchDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLine;

/**
 * @author jchengli
 *
 */


public interface ScholarService {
<<<<<<< HEAD
	
	ScholarEntity get(long id); 
	
	List<VScholarDto> findScholars();
	
	List<VScholarDto> saveOrUpdateScholars (List<VScholarDto> dto);
	
	List<VScholarTimeLine> findScholarsByDateTimeline (VScholarSearchDto date);
=======

  ScholarEntity get(long id);

  List<VScholarDto> findScholars();

  List<VScholarDto> saveOrUpdateScholars (List<VScholarDto> dto);

  void deleteById(long id);

>>>>>>> develop
}
