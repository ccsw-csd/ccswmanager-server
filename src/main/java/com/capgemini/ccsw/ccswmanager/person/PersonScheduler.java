package com.capgemini.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capgemini.ccsw.ccswmanager.centertranscode.CenterTranscodeService;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.tperson.TPersonService;
import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

@Component
public class PersonScheduler {

    private static final int ACTIVE_STATUS = 1;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TPersonService tpersonService;

    @Autowired
    CenterTranscodeService centerTranscodeService;

    @Scheduled(cron = "${personScheduler.cron}")
    public void scheduledTask() {
        List<PersonEntity> activePersons = personRepository.findByActive(ACTIVE_STATUS);

        List<String> usernames = new ArrayList<String>();
        List<String> sagaCodes = new ArrayList<String>();
        for (PersonEntity personEntity : activePersons) {
            usernames.add(personEntity.getUsername());
            sagaCodes.add(personEntity.getSaga());
        }
        List<TPersonEntity> list = tpersonService.matchedTPersonWithPersonUsernameAndSaga(usernames, sagaCodes);

        List<PersonEntity> filteredList = activePersons.stream()
                .filter(two -> list.stream()
                        .anyMatch(one -> (checkUsernameOrSagaAreSame(two, one)
                                && checkChangedParametersComparingPersonAndTperson(two, one))))
                .collect(Collectors.toList());
        personRepository.saveAll(filteredList);

    }

    private boolean checkUsernameOrSagaAreSame(PersonEntity one, TPersonEntity two) {
        boolean tmp = false;
        if ((one != null && two != null && one.getUsername() != null && one.getUsername().equals(two.getUsername()))
                || (one.getSaga() != null && !two.getSaga().isEmpty() && one.getSaga().equals(two.getSaga()))) {
            return true;
        }
        return tmp;
    }

    private boolean checkChangedParametersComparingPersonAndTperson(PersonEntity personEntity,
            TPersonEntity entityTmp) {
        boolean tmp = false;
        if (entityTmp != null) {
            if (!entityTmp.getName().isEmpty() && !entityTmp.getName().equals(personEntity.getName())) {
                personEntity.setName(entityTmp.getName());
                tmp = true;
            }
            if (!entityTmp.getLastname().isEmpty() && !entityTmp.getLastname().equals(personEntity.getLastname())) {
                personEntity.setLastname(entityTmp.getLastname());
                tmp = true;
            }

            if (entityTmp.getCenterTranscode() != null && !entityTmp.getCenterTranscode().getCenter().getName()
                    .equals(personEntity.getCenter().getName())) {
                personEntity.setCenter(entityTmp.getCenterTranscode().getCenter());
                tmp = true;
            }
            if (!entityTmp.getSaga().isEmpty() && !entityTmp.getSaga().equals(personEntity.getSaga())) {
                personEntity.setSaga(entityTmp.getSaga());
                tmp = true;
            }
            if (!entityTmp.getGrade().isEmpty() && !personEntity.getGrade().isEmpty()) {
                if (!(entityTmp.getGrade().substring(0, 1).equals(personEntity.getGrade().substring(0, 1)))) {
                    personEntity.setGrade(entityTmp.getGrade());
                    tmp = true;
                }
            }
        }
        return tmp;
    }
}
