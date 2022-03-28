package com.capgemini.ccsw.ccswmanager.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.center.CenterService;
import com.capgemini.ccsw.ccswmanager.center_transcode.CenterTranscodeService;
import com.capgemini.ccsw.ccswmanager.center_transcode.CenterTranscodeServiceImpl;
import com.capgemini.ccsw.ccswmanager.center_transcode.model.CenterTranscodeEntity;
import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.model.PersonDto;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.scholar.ScholarService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.tperson.TPersonService;
import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    TPersonService tpersonService;

    @Autowired
    ScholarService scholarService;

    @Autowired
    CenterService centerService;

    @Autowired
    CenterTranscodeServiceImpl centerTranscodeServiceImpl;
    
    @Autowired
    private BeanMapper beanMapper;

    @Override
    public List<PersonDto> findPersons() {

        return this.beanMapper.mapList(this.personRepository.findAll(), PersonDto.class);
    }

    @Override
    public PersonEntity get(long id) {

        return this.personRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonDto> findByFilter(String filter) {

        List<TPersonEntity> persons = new ArrayList<>();
        List<PersonDto> personsToReturn = new ArrayList<>();
        List<TPersonEntity> personsLike = this.tpersonService.findAllTpersonsFromFilters(filter);
        List<PersonEntity> allPersons = this.personService.findAll();

        Set<String> personsToRemove = allPersons.stream().map(PersonEntity::getUsername).collect(Collectors.toSet());
        persons = personsLike.stream().filter(person -> !personsToRemove.contains(person.getUsername())).collect(Collectors.toList());

        personsToReturn = this.beanMapper.mapList(persons, PersonDto.class);

        PersonDto newPerson = new PersonDto();

        String nameLastname[] = filter.split(" ");
        StringBuilder lastname = new StringBuilder();
        for (int i = 1; i < nameLastname.length; i++) {
            lastname.append(nameLastname[i] + " ");
        }
        newPerson.setName(nameLastname[0]);
        newPerson.setLastname(EMPTY_STRING.equals(lastname.toString()) ? EMPTY_STRING : lastname.toString());

        personsToReturn.add(0, newPerson);

        return personsToReturn;
    }

    @Override
    public List<PersonDto> saveOrUpdatePersons(List<PersonDto> personListTo) {

        personListTo.forEach(personTo -> {

            Objects.requireNonNull(personTo, "person");

            PersonEntity person = null;

            if (personTo.getId() != null) {
                person = get(personTo.getId());
            }

            if (person == null) {
                person = new PersonEntity();
            }

            if (personTo.getDelete() != null && personTo.getDelete() == true) {

                ScholarEntity scholarPerson = this.scholarService.get(personTo.getId());
                if (scholarPerson != null) {
                    this.scholarService.deleteById(scholarPerson.getId());
                }

                this.personRepository.deleteById(personTo.getId());

            } else {
                BeanUtils.copyProperties(personTo, person, "id", "center");

                person.setCenter(this.centerService.getById(personTo.getCenter().getId()));

                this.personRepository.save(person);
            }
        });

        return findPersons();
    }

    @Override
    public List<PersonEntity> findScholars(String department, String grade, int active) {
        return this.personRepository.findByDepartmentAndGradeIsNullOrGradeIsAndActiveIsOrderByUsernameAsc(department, grade, active);
    }

    @Override
    public List<PersonEntity> findContracts(String department, String grade, int active) {
        return this.personRepository.findByDepartmentAndGradeIsNotNullAndGradeIsNotAndActiveIsOrderByUsernameAsc(department, grade, active);
    }

    @Override
    public List<PersonEntity> findAll() {
        return this.personRepository.findAllByOrderByUsernameAsc();
    }
    @Override
	@Scheduled(cron = "0 0/10 * * * *")
	public void scheduledTask() {
		List<PersonEntity> activePersons = personRepository.findByActiveTrue();
		try {
		for (PersonEntity personEntity : activePersons) {
			List<TPersonEntity> list = tpersonService.matchedTPersonWithPersonUsernameAndSaga(personEntity.getUsername(), personEntity.getSaga());
			if (!list.isEmpty()) {
				TPersonEntity entityTmp = list.get(0);
				if(!entityTmp.getName().isEmpty()) {
					if (!entityTmp.getName().equals(personEntity.getName())) {
						personEntity.setName(entityTmp.getName());
					}
				}
				if (!entityTmp.getLastname().isEmpty()) {
					if(!entityTmp.getLastname().equals(personEntity.getLastname())){
						personEntity.setLastname(entityTmp.getLastname());
					}
				}
				if(!entityTmp.getCenterTranscode().isEmpty()) {
					if(!centerTranscodeServiceImpl.findByName(entityTmp.getCenterTranscode()).getCenter().getName().equals(personEntity.getCenter().getName())){
						personEntity.setCenter(centerTranscodeServiceImpl.findByName(entityTmp.getCenterTranscode()).getCenter());
					}
				}
				if(!entityTmp.getSaga().isEmpty()) {
					if(!entityTmp.getSaga().equals(personEntity.getSaga())){
						personEntity.setSaga(entityTmp.getSaga());
					}
				}
				if(!entityTmp.getGrade().isEmpty() && !personEntity.getGrade().isEmpty()) {
					if(!(entityTmp.getGrade().charAt(0) == personEntity.getGrade().charAt(0))){
						personEntity.setGrade(entityTmp.getGrade());
					}
				}
				personRepository.save(personEntity);
			}
		}}
		catch(Exception e) {
			e.printStackTrace();
		}
			
	}
}
