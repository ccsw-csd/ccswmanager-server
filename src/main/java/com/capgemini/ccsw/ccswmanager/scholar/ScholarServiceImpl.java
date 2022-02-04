package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;

import org.springframework.data.jpa.repository.JpaRepository;



@Service
public class ScholarServiceImpl implements ScholarService {

	  @Autowired
	  ScholarRepository scholarRepository;

	  @Autowired
	  private BeanMapper beanMapper;

	  @Override
	  public List<ScholarDto> findScholars() {

	    return this.beanMapper.mapList(this.scholarRepository.findAll(), ScholarDto.class);
	  }
	  /*
	  @Override
	    public ScholarDto get(Long id) {
	        return this.beanMapper.mapList(this.scholarRepository.findById(id).orElse(null), ScholarDto.class);
 
	    }
	    */
	    
	   
	    //public void updateScholar(Integer id, Integer person_id, String username, String name, String lastname, String customer, Integer hours, String details, Date start_date, Date end_date, String title, String action, Integer active) {

		
		@Override
		public void saveOrUpdateScholar(ScholarEntity scholar) {
			
			Optional<ScholarEntity> scholarById = scholarRepository.findById(scholar.getId());
						
			if(scholarById.isPresent()) {
				
				scholarRepository.save(scholar);
			}
				
			else {
				ScholarEntity scholarE = null;
				scholarE.setName(scholar.getName());
				scholarE.setActive(scholar.getActive());
				scholarE.setCustomer(scholar.getCustomer());
				scholarE.setDetails(scholar.getDetails());
				scholarE.setEnd_date(scholar.getEnd_date());
				scholarE.setStart_date(scholar.getStart_date());
				scholarE.setHours(scholar.getHours());
				scholarE.setId(scholar.getId());
				scholarE.setLastname(scholar.getLastname());
				scholarE.setName(scholar.getName());
				scholarE.setPerson_id(scholar.getPerson_id());
				scholarE.setStart_date(scholar.getStart_date());
				scholarE.setTitle(scholar.getTitle());
				scholarE.setUsername(scholar.getUsername());
				
				scholarRepository.save(scholar);
			}
				        
		}


	@Override
	public Optional<ScholarEntity> findById(Integer id) {
		
		
		
        //return scholarRepository.findById(id);

		//return this.beanMapper.map(this.scholarRepository.findById(id).orElse(null), ScholarDto.class);

	}
}
