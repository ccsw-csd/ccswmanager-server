package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;

public interface ScholarService {
	
	ScholarEntity get(long id); 
	
	List<VScholarDto> findScholars();
	
	List<VScholarDto> saveOrUpdateScholars (List<VScholarDto> dto);
}
