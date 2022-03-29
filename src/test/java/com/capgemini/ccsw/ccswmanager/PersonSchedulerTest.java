package com.capgemini.ccsw.ccswmanager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.ccswmanager.center.CenterController;
import com.capgemini.ccsw.ccswmanager.center.CenterService;
import com.capgemini.ccsw.ccswmanager.centertranscode.CenterTranscodeService;
import com.capgemini.ccsw.ccswmanager.person.PersonScheduler;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.tperson.TPersonService;

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

        assertTrue(personService.findAll().stream().filter(item -> item.getUsername().equals("TEMPUSERNAME"))
                .findFirst().orElse(null).getGrade().equals("D"));
    }
}
