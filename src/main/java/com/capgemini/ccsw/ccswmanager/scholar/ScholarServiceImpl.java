package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;

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
    public void deleteById(long id) {

    this.scholarRepository.deleteById(id);
  }
}
