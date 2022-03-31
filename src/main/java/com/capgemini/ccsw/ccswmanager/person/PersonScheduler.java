package com.capgemini.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
                .filter(personEntity -> list.stream()
                        .anyMatch(tpersonEntity -> (checkUsernameOrSagaAreSame(personEntity, tpersonEntity)
                                && checkChangedParametersComparingPersonAndTperson(personEntity, tpersonEntity))))
                .collect(Collectors.toList());
        personRepository.saveAll(filteredList);

    }

    private boolean checkUsernameOrSagaAreSame(PersonEntity personEntity, TPersonEntity tpersonEntity) {
        if ((personEntity != null && tpersonEntity != null) && ((personEntity.getUsername() != null
                && personEntity.getUsername().equals(tpersonEntity.getUsername()))
                || (personEntity.getSaga() != null && !tpersonEntity.getSaga().isEmpty()
                        && personEntity.getSaga().equals(tpersonEntity.getSaga())))) {
            return true;
        }
        return false;
    }

    private boolean checkChangedParametersComparingPersonAndTperson(PersonEntity personEntity,
            TPersonEntity entityTmp) {
        boolean tmp = false;
        if (!entityTmp.getName().isEmpty() && !entityTmp.getName().equals(personEntity.getName())) {
            personEntity.setName(entityTmp.getName());
            tmp = true;
        }
        if (!entityTmp.getLastname().isEmpty() && !entityTmp.getLastname().equals(personEntity.getLastname())) {
            personEntity.setLastname(entityTmp.getLastname());
            tmp = true;
        }

        if (entityTmp.getCenterTranscode() != null
                && entityTmp.getCenterTranscode().getCenter().getId() != personEntity.getCenter().getId()) {
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
        return tmp;
    }
}
