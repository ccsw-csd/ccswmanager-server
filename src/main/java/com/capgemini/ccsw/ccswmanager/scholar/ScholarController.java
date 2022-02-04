package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import java.util.Optional;



@RequestMapping(value = "/scholar/")
@RestController
public class ScholarController {

	
	@Autowired
	private ScholarService scholarService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<ScholarDto> findScholars() {

		return this.scholarService.findScholars();

	  }
	
	@GetMapping
	public ScholarEntity get(@PathVariable Integer id) {
		Optional<ScholarEntity> scholar = scholarService.findById(id);
		if(scholar.isPresent()) {
			return scholar.get();
		}else {
			throw new RuntimeException("Scholar not found for the id "+id);
		}
	}
	 

	@PutMapping
    public void save(@PathVariable(name = "id", required = false) Integer id, @RequestBody ScholarDto dto) {

        scholarService.updateScholar(id, dto);
    }
	
	@PostMapping
	public ScholarEntity save(@RequestBody ScholarEntity scholar) {
		return scholar.save(scholar);
	}
	
}
