package com.capgemini.ccsw.ccswmanager.scholar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarSearchDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLine;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;

/**
 * @author jchengli
 *
 */


@Service
public class ScholarServiceImpl implements ScholarService {

	  @Autowired
	  ScholarRepository scholarRepository;
	  @Autowired
	  VScholarRepository vScholarRepository;
	  @Autowired
	  PersonService personService;

	  @Autowired
	  private BeanMapper beanMapper;

	  @Override
	  public ScholarEntity get(long id) {

		  return this.scholarRepository.getByPerson_Id(id);
	  }
	  
	  @Override
	  public List<VScholarDto> findScholars() {

	    return this.beanMapper.mapList(this.vScholarRepository.findAll(), VScholarDto.class);
	  }
	  
	  @Override
	  public List<VScholarDto> saveOrUpdateScholars (List<VScholarDto> dtoList) {
		  dtoList.forEach(dto -> {
			  Objects.requireNonNull(dto, "scholar");
			  ScholarEntity scholar = null;
			  
			  if(dto.getId() != null)
				  scholar = get(dto.getId());
			  if(scholar == null)
				  scholar = new ScholarEntity();
			  
			  BeanUtils.copyProperties(dto, scholar, "id", "person");
			  scholar.setPerson(this.personService.get(dto.getId()));
			  
			  this.scholarRepository.save(scholar);
		  });
		  return findScholars();
	  }
	  
	  @Override
	  public List<VScholarTimeLine> findScholarsByDateTimeline(VScholarSearchDto date) {
		  List<VScholarDto> vscholars = this.beanMapper.mapList(this.vScholarRepository.findByDate(date.getStartDate(), date.getEndDate()), VScholarDto.class);
		  List<VScholarTimeLine> vscholarsTimeLine = new ArrayList<VScholarTimeLine>();

		  //sort scholars looking startDate
		  Collections.sort(vscholars,new CompareTimeLine());		  
		  for(VScholarDto vscholar : vscholars) {
			  VScholarTimeLine vscholarTimeline = new VScholarTimeLine();
			  ArrayList<Long> Y = new ArrayList<Long>();
			

			  vscholarTimeline.setX(vscholar.getUsername());
			  if(vscholar.getStartDate() != null && vscholar.getEndDate() != null) {
				  Y.add(vscholar.getStartDate().getTime());
				  Y.add(vscholar.getEndDate().getTime());
				  vscholarTimeline.setY(Y);
			  }
			  else if(vscholar.getStartDate() != null) {
				  Y.add(vscholar.getStartDate().getTime());
				  vscholarTimeline.setY(Y);
			  }
			  else if(vscholar.getEndDate() != null) {
				  Y.add(vscholar.getEndDate().getTime());
				  vscholarTimeline.setY(Y);
			  }
			  else {
				  vscholarTimeline.setY(Y);
			  }
			  if(vscholar.getAction() != null) {
				  if((Integer)vscholar.getAction() == 2 || (Integer)vscholar.getAction() == 1) {
					  vscholarTimeline.setFillColor("#00E396");
				  }
				  else if((Integer)vscholar.getAction() == 0) {
					  vscholarTimeline.setFillColor("#FF4560");
				  }
				  else{
					  vscholarTimeline.setFillColor("#008FFB");
				  }
			  }
			  else{
				  vscholarTimeline.setFillColor("#008FFB");
			  }

			  vscholarsTimeLine.add(vscholarTimeline);
		  }
		  
		  return vscholarsTimeLine;
	  }
}
class CompareTimeLine implements Comparator<VScholarDto> {
	
	@Override
	public int compare(VScholarDto o1, VScholarDto o2) {
		// TODO Auto-generated method stub
		return o1.getStartDate().compareTo(o2.getStartDate());
	}
}
