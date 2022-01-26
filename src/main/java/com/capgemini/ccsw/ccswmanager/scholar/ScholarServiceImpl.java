package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;

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
}
