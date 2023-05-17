package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;
import com.ccsw.ccswmanager.ldap.model.ListsLdapPersonDto;
import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.tmember.TMemberService;
import com.ccsw.ccswmanager.tmember.model.TMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dapalmie
 *
 */
@Service
public class LdapServiceImpl implements LdapService {

    public static final String EMPTY_STRING = "";
    public static final Integer ACTIVE_TRUE = 1;

    private static final String DEPARTMENT_CODE = "CCSw";

    private static final String CONTRACT_GROUP = "dlesccsw";

    private static final String INTERNS_GROUP = "dlescca.becarios";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    TMemberService tmemberService;

    @Autowired
    PersonService personService;

    @Autowired
    InternService internService;

    @Override
    public Boolean checkPersonsToSync() {

        ListsLdapPersonDto personLists = this.comparePersons();

        return personLists.getLdapToPersons().isEmpty() && personLists.getPersonsToLdap().isEmpty();
    }

    @Override
    public Boolean checkInternsToSync() {

        ListsLdapPersonDto personLists = this.compareInterns();

        return personLists.getLdapToPersons().isEmpty() && personLists.getPersonsToLdap().isEmpty();
    }

    @Override
    public List<String> findPersonUsernames() {

        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);

        return persons.stream().map(PersonEntity::getUsername).collect(Collectors.toList());
    }

    @Override
    public List<String> findInternUsernames() {

        List<InternEntity> interns = this.internService.findNotEmptyActives();

        return interns.stream().map(InternEntity::getUsername).collect(Collectors.toList());
    }

    @Override
    public ListsLdapPersonDto comparePersons() {

        List<LdapPersonDto> ldapToPersons = new ArrayList<>();
        List<LdapPersonDto> personsToLdap = new ArrayList<>();

        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        List<TMemberEntity> members = this.tmemberService.findTMembers(CONTRACT_GROUP);

        Set<String> usersToRemoveLdapPerson = persons.stream().map(PersonEntity::getUsername).collect(Collectors.toSet());
        List<TMemberEntity> personsComparedLdapPerson = members.stream().filter(member -> !usersToRemoveLdapPerson.contains(member.getUserCn())).collect(Collectors.toList());

        Set<String> usersToRemovePersonLdap = members.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());
        List<PersonEntity> personsComparedPersonLdap = persons.stream().filter(person -> !usersToRemovePersonLdap.contains(person.getUsername())).collect(Collectors.toList());

        personsComparedLdapPerson.forEach(person -> ldapToPersons.add(new LdapPersonDto(person.getTperson().getName(), person.getTperson().getLastname(), person.getTperson().getUsername())));
        personsComparedPersonLdap.forEach(person -> personsToLdap.add(new LdapPersonDto(person.getName(), person.getLastname(), person.getUsername())));

        return new ListsLdapPersonDto(ldapToPersons, personsToLdap);

    }

    @Override
    public ListsLdapPersonDto compareInterns() {

        List<LdapPersonDto> ldapToPersons = new ArrayList<>();
        List<LdapPersonDto> personsToLdap = new ArrayList<>();

        List<InternEntity> interns = this.internService.findNotEmptyActives();
        List<TMemberEntity> members = this.tmemberService.findTMembers(INTERNS_GROUP);

        Set<String> usersToRemoveLdapIntern = interns.stream().map(InternEntity::getUsername).collect(Collectors.toSet());
        List<TMemberEntity> personsCompared = members.stream().filter(member -> !usersToRemoveLdapIntern.contains(member.getUserCn())).collect(Collectors.toList());

        Set<String> usersToRemoveInternLdap = members.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());
        List<InternEntity> internsCompared = interns.stream().filter(intern -> !usersToRemoveInternLdap.contains(intern.getUsername())).collect(Collectors.toList());
        personsCompared.forEach(person -> ldapToPersons.add(new LdapPersonDto(person.getTperson().getName(), person.getTperson().getLastname(), person.getTperson().getUsername())));
        internsCompared.forEach(intern -> personsToLdap.add(new LdapPersonDto(intern.getName(), intern.getLastname(), intern.getUsername())));

        return new ListsLdapPersonDto(ldapToPersons, personsToLdap);

    }

    @Override
    public Boolean checkPersons() {

        return this.compareLdapToPersons().size() <= 0 && this.comparePersonsToLdap().size() <= 0;
    }

    @Override
    public Boolean checkInterns() {

        return this.compareLdapToInterns().size() <= 0 && this.compareInternsToLdap().size() <= 0;
    }

    @Override
    public List<LdapPersonDto> compareLdapToPersons() {

        List<LdapPersonDto> ldapToPersons = new ArrayList<>();

        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        List<TMemberEntity> members = this.tmemberService.findTMembers(CONTRACT_GROUP);

        Set<String> usersToRemove = persons.stream().map(PersonEntity::getUsername).collect(Collectors.toSet());
        List<TMemberEntity> personsCompared = members.stream().filter(member -> !usersToRemove.contains(member.getUserCn())).collect(Collectors.toList());

        personsCompared.forEach(person -> ldapToPersons.add(new LdapPersonDto(person.getTperson().getName(), person.getTperson().getLastname(), person.getTperson().getUsername())));

        return ldapToPersons;
    }

    @Override
    public List<LdapPersonDto> comparePersonsToLdap() {

        List<LdapPersonDto> personsToLdap = new ArrayList<>();

        List<TMemberEntity> members = this.tmemberService.findTMembers(CONTRACT_GROUP);
        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);

        Set<String> usersToRemove = members.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());
        List<PersonEntity> personsCompared = persons.stream().filter(person -> !usersToRemove.contains(person.getUsername())).collect(Collectors.toList());

        personsCompared.forEach(person -> personsToLdap.add(new LdapPersonDto(person.getName(), person.getLastname(), person.getUsername())));

        return personsToLdap;
    }

    @Override
    public List<LdapPersonDto> compareLdapToInterns() {

        List<LdapPersonDto> ldapToPersons = new ArrayList<>();

        List<InternEntity> interns = this.internService.findNotEmptyActives();
        List<TMemberEntity> members = this.tmemberService.findTMembers(INTERNS_GROUP);

        Set<String> usersToRemove = interns.stream().map(InternEntity::getUsername).collect(Collectors.toSet());
        List<TMemberEntity> personsCompared = members.stream().filter(member -> !usersToRemove.contains(member.getUserCn())).collect(Collectors.toList());

        personsCompared.forEach(person -> ldapToPersons.add(new LdapPersonDto(person.getTperson().getName(), person.getTperson().getLastname(), person.getTperson().getUsername())));

        return ldapToPersons;
    }

    @Override
    public List<LdapPersonDto> compareInternsToLdap() {

        List<LdapPersonDto> personsToLdap = new ArrayList<>();

        List<TMemberEntity> members = this.tmemberService.findTMembers(INTERNS_GROUP);
        List<InternEntity> interns = this.internService.findNotEmptyActives();

        Set<String> usersToRemove = members.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());
        List<InternEntity> internsCompared = interns.stream().filter(intern -> !usersToRemove.contains(intern.getUsername())).collect(Collectors.toList());

        internsCompared.forEach(intern -> personsToLdap.add(new LdapPersonDto(intern.getName(), intern.getLastname(), intern.getUsername())));

        return personsToLdap;
    }

}
