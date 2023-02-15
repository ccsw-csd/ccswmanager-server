package com.ccsw.ccswmanager.ldap;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.ldap.model.LdapPersonDto;
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

    private static final String SCHOLARS_GROUP = "dlesccsw.becarios";

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

    @Deprecated
    @Override
    public Boolean check() {

        return this.compareLdapToPersons(true).size() <= 0 && this.compareLdapToPersons(false).size() <= 0 && this.comparePersonsToLdap(true).size() <= 0 && this.comparePersonsToLdap(false).size() <= 0;
    }

    @Deprecated
    @Override
    public List<LdapPersonDto> compareLdapToPersons(boolean contract) {

        List<TMemberEntity> tmembers = new ArrayList<>();
        List<PersonEntity> persons = new ArrayList<>();
        List<LdapPersonDto> ldapToPersons = new ArrayList<>();

        if (contract) {
            persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
            tmembers = this.tmemberService.findTMembers(CONTRACT_GROUP);
        } else {
            persons = this.personService.findScholars(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
            tmembers = this.tmemberService.findTMembers(SCHOLARS_GROUP);
        }

        List<TMemberEntity> personsCompared = new ArrayList<>();

        Set<String> usersToRemove = persons.stream().map(PersonEntity::getUsername).collect(Collectors.toSet());

        personsCompared = tmembers.stream().filter(tmember -> !usersToRemove.contains(tmember.getUserCn())).collect(Collectors.toList());

        personsCompared.forEach(person -> ldapToPersons.add(new LdapPersonDto(person.getTperson().getName(), person.getTperson().getLastname(), person.getTperson().getUsername())));

        return ldapToPersons;
    }

    @Deprecated
    @Override
    public List<LdapPersonDto> comparePersonsToLdap(boolean contract) {

        List<TMemberEntity> tmembers = new ArrayList<>();
        List<PersonEntity> persons = new ArrayList<>();
        List<LdapPersonDto> personsToLdap = new ArrayList<>();
        if (contract) {
            tmembers = this.tmemberService.findTMembers(CONTRACT_GROUP);
            persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        } else {
            tmembers = this.tmemberService.findTMembers(SCHOLARS_GROUP);
            persons = this.personService.findScholars(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        }

        List<PersonEntity> personsCompared = new ArrayList<>();
        Set<String> usersToRemove = tmembers.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());

        personsCompared = persons.stream().filter(person -> !usersToRemove.contains(person.getUsername())).collect(Collectors.toList());

        personsCompared.forEach(person -> personsToLdap.add(new LdapPersonDto(person.getName(), person.getLastname(), person.getUsername())));

        return personsToLdap;
    }

    @Deprecated
    @Override
    public List<String> findUsernames(boolean contract) {
        List<PersonEntity> persons;
        if (contract) {
            persons = this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        } else {
            persons = this.personService.findScholars(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);
        }

        return persons.stream().map(PersonEntity::getUsername).collect(Collectors.toList());
    }

    @Override
    public Boolean checkPersons() {

        return this.compareLdapToPersons().size() <= 0 && this.comparePersonsToLdap().size() <= 0;
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
    public List<String> findPersonUsernames() {

        List<PersonEntity> persons =  this.personService.findContracts(DEPARTMENT_CODE, EMPTY_STRING, ACTIVE_TRUE);

        return persons.stream().map(PersonEntity::getUsername).collect(Collectors.toList());
    }

    @Override
    public Boolean checkInterns() {

        return this.compareLdapToInterns().size() <= 0 && this.compareInternsToLdap().size() <= 0;
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

    @Override
    public List<String> findInternUsernames() {

        List<InternEntity> interns = this.internService.findNotEmptyActives();

        return interns.stream().map(InternEntity::getUsername).collect(Collectors.toList());
    }

}
