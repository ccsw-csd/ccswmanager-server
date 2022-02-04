package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;

public interface ScholarService extends JpaRepository<ScholarEntity, Integer> {
	
	List<ScholarDto> findScholars();
	
	void saveOrUpdateScholar(ScholarEntity scholar);
	
    Optional<ScholarEntity> findById(Integer id);

    public void updateScholar(Integer id, Integer person_id, String username, String name, String lastname, String customer, Integer hours, String details, Date start_date, Date end_date, String title, String action, Integer active);

	ScholarDto get(Integer id);
    
}
