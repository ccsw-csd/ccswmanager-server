package com.capgemini.ccsw.ccswmanager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.ccswmanager.center.CenterController;
import com.capgemini.ccsw.ccswmanager.center.CenterService;
import com.capgemini.ccsw.ccswmanager.center.model.CenterDto;
import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;
import com.capgemini.ccsw.ccswmanager.center_transcode.CenterTranscodeService;
import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;
import com.capgemini.ccsw.ccswmanager.person.PersonScheduler;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.tperson.TPersonService;
import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

@SpringBootTest
@Transactional
public class PersonSchedulerTest {

	@Autowired
	PersonScheduler personScheduler;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	TPersonService tpersonService;
	
	@Autowired
	CenterService centerService;
	
	@Autowired
	CenterController centerController;
	
	@Autowired
	CenterTranscodeService centerTranscodeService;
	
	@Test
	public void personSchedulerTaskTest() {
		assertNotNull(personScheduler);
		assertNotNull(personService);
		assertNotNull(tpersonService);
		assertNotNull(centerService);
		
		personScheduler.scheduledTask();
		
		assertTrue(personService.findAll().stream().filter(item->item.getUsername().equals("TEMPUSERNAME")).findFirst().orElse(null).getGrade().equals("D"));
	}
}
