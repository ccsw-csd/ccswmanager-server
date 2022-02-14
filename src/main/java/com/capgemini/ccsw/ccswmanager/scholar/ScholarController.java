package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.scholar.ScholarService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;


@RequestMapping(value = "/scholar/")
@RestController
public class ScholarController {

	@Autowired
	private ScholarService scholarService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<ScholarDto> findScholars() {

		return this.scholarService.findScholars();

	  }
}
