package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;


@RequestMapping(value = "/scholar/")
@RestController
public class ScholarController {
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ScholarService scholarService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<VScholarDto> findScholars() {

		return this.scholarService.findScholars();
	}
	
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public List<VScholarDto> saveOrUpdateScholars(@RequestBody List<VScholarDto> dto) {

    	return this.scholarService.saveOrUpdateScholars(dto);
    }
     
}
