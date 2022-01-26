package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;

public interface ScholarService {
	
	List<ScholarDto> findScholars();
}
