package com.ccsw.ccswmanager.person;

import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.person.model.PersonReportDto;
import com.ccsw.ccswmanager.person.model.PersonReportFactDto;
import com.ccsw.ccswmanager.person.model.PersonReportSectionDto;
import com.ccsw.ccswmanager.tperson.TPersonService;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.util.stream.Collectors.*;

@Component
public class PersonReportScheduler {

    @Value("${teams.webhook}")
    private String path;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TPersonService tpersonService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "${personReportScheduler.cron}")
    public void scheduledTask() {

        LocalDateTime start = LocalDateTime.now().minusMonths(1).with(firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().minusMonths(1).with(lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<PersonEntity> updatedPersons = personRepository.findByUpdatedAtBetween(start, end);

        List<TPersonEntity> updaters = tpersonService.matchedTPersonWithPersonUsernameAndSaga(updatedPersons.stream().map(PersonEntity::getUpdatedBy).collect(Collectors.toList()), new ArrayList<>());
        Map<String, List<PersonEntity>> updatedPersonsByUpdater = updatedPersons.stream().collect(groupingBy(PersonEntity::getUpdatedBy));

        sendReport(composeMessage(updatedPersonsByUpdater, updaters, start));
    }

    private PersonReportDto composeMessage(Map<String, List<PersonEntity>> updatedPersonsByUpdater, List<TPersonEntity> updaters, LocalDateTime start){

        List<PersonReportFactDto> facts = new ArrayList<>();

        updatedPersonsByUpdater.forEach((key, value) -> {
            PersonReportFactDto fact = new PersonReportFactDto();

            Optional<TPersonEntity> updater = updaters.stream().filter(user -> user.getUsername().equals(key)).findFirst();

            fact.setName(updater.map(user -> user.getName() + " " + user.getLastname()).orElse(key));
            fact.setValue(value.stream().map(person -> person.getName() + " " + person.getLastname()).collect(toList()).toString());

            facts.add(fact);
        });

        PersonReportSectionDto section = new PersonReportSectionDto();
        section.setActivityTitle("Listado por actualizador y personas actualizadas (" + start.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + ")");
        section.setFacts(facts);

        List<PersonReportSectionDto> sections = new ArrayList<>();
        sections.add(section);

        PersonReportDto message = new PersonReportDto();
        message.setType("MessageCard");
        message.setSummary("Personas actualizadas");
        message.setSections(sections);

        return message;
    }

    private void sendReport(PersonReportDto request) {

        restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<>(request), Void.class);
    }

}
