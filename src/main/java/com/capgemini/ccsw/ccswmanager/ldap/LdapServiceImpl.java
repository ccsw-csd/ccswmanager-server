package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPersonDto;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.tmember.TMemberService;
import com.capgemini.ccsw.ccswmanager.tmember.model.TMemberEntity;
import com.capgemini.ccsw.ccswmanager.tperson.TPersonService;

/**
 * TODO asolerpa This type ...
 *
 */
@Service
public class LdapServiceImpl implements LdapService {

    private static final int ACTIVE_TRUE = 1;

    private static final String DEPARTMENT_CODE = "CCSw";

    private static final String SCHOLARS_GROUP = "dlesccsw.becarios";

    private static final String CONTRACT_GROUP = "dlesccsw";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    TMemberService tmemberService;

    @Autowired
    PersonService personService;

    @Autowired
    TPersonService tpersonService;

    @Override
    public Boolean check() {

        /*
         * List<LdapPersonDto> persons = this.compareLdapToPersons();
         * 
         * List<LdapPersonDto> ldap = this.compareLdapToPersons();
         * 
         * List<LdapPersonDto> personsScholars = this.comparePersonsToLdapScholars();
         * List<LdapPersonDto> ldapScholars = this.compareLdapToPersonsScholars();
         */
        if (this.compareLdapToPersons().size() > 0 || this.comparePersonsToLdapScholars().size() > 0
                || this.compareLdapToPersons().size() > 0 || this.compareLdapToPersonsScholars().size() > 0)
            return false;
        else
            return true;
    }

    @Override
    public List<LdapPersonDto> compareLdapToPersons() {
        List<LdapPersonDto> ldapToPersons = new ArrayList<LdapPersonDto>();
        List<TMemberEntity> tmembers = this.tmemberService.findTMembers(CONTRACT_GROUP);

        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, "", ACTIVE_TRUE);

        List<TMemberEntity> personsCompared = new ArrayList<TMemberEntity>();

        personsCompared = tmembers.stream().filter(
                tmember -> persons.stream().noneMatch(person -> tmember.getUserCn().equals(person.getUsername())))
                .collect(Collectors.toList());

        for (int i = 0; i < personsCompared.size(); i++) {
            ldapToPersons.add(new LdapPersonDto(personsCompared.get(i).getTperson().getName(),
                    personsCompared.get(i).getTperson().getLastname(),
                    personsCompared.get(i).getTperson().getUsername()));
        }

        return ldapToPersons;

    }

    @Override
    public List<LdapPersonDto> comparePersonsToLdap() {

        List<TMemberEntity> tmembers = this.tmemberService.findTMembers(CONTRACT_GROUP);
        List<PersonEntity> persons = this.personService.findContracts(DEPARTMENT_CODE, "", ACTIVE_TRUE);

        List<LdapPersonDto> personsToLdap = new ArrayList<LdapPersonDto>();

        List<PersonEntity> personsCompared = new ArrayList<PersonEntity>();
        Set<String> usersToRemove = tmembers.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());

        personsCompared = persons.stream().filter(person -> !usersToRemove.contains(person.getUsername()))
                .collect(Collectors.toList());

        personsCompared.forEach(person -> personsToLdap
                .add(new LdapPersonDto(person.getName(), person.getLastname(), person.getUsername())));

        return personsToLdap;

    }

    @Override
    public List<LdapPersonDto> compareLdapToPersonsScholars() {
        List<LdapPersonDto> ldapToPersonsScholars = new ArrayList<LdapPersonDto>();
        List<TMemberEntity> tmembers = this.tmemberService.findTMembers(SCHOLARS_GROUP);

        List<PersonEntity> persons = this.personService.findScholars(DEPARTMENT_CODE, "", ACTIVE_TRUE);

        List<TMemberEntity> personsCompared = new ArrayList<TMemberEntity>();

        personsCompared = tmembers.stream().filter(
                tmember -> persons.stream().noneMatch(person -> tmember.getUserCn().equals(person.getUsername())))
                .collect(Collectors.toList());

        for (int i = 0; i < personsCompared.size(); i++) {
            ldapToPersonsScholars.add(new LdapPersonDto(personsCompared.get(i).getTperson().getName(),
                    personsCompared.get(i).getTperson().getLastname(),
                    personsCompared.get(i).getTperson().getUsername()));
        }

        return ldapToPersonsScholars;

    }

    @Override
    public List<LdapPersonDto> comparePersonsToLdapScholars() {

        List<TMemberEntity> tmembers = this.tmemberService.findTMembers(SCHOLARS_GROUP);
        List<PersonEntity> persons = this.personService.findScholars(DEPARTMENT_CODE, "", ACTIVE_TRUE);

        List<LdapPersonDto> personsToLdapScholars = new ArrayList<LdapPersonDto>();

        List<PersonEntity> personsCompared = new ArrayList<PersonEntity>();
        Set<String> usersToRemove = tmembers.stream().map(TMemberEntity::getUserCn).collect(Collectors.toSet());

        personsCompared = persons.stream().filter(person -> !usersToRemove.contains(person.getUsername()))
                .collect(Collectors.toList());

        personsCompared.forEach(person -> personsToLdapScholars
                .add(new LdapPersonDto(person.getName(), person.getLastname(), person.getUsername())));

        return personsToLdapScholars;
    }

    public List<String> findUsernames(boolean contract) {
        List<PersonEntity> persons = new ArrayList<PersonEntity>();
        if (contract)
            persons = this.personService.findContracts(DEPARTMENT_CODE, "", ACTIVE_TRUE);
        else
            persons = this.personService.findScholars(DEPARTMENT_CODE, "", ACTIVE_TRUE);

        List<String> usernamesToReturn = new ArrayList<>();

        persons.forEach(person -> usernamesToReturn.add(person.getUsername()));

        return usernamesToReturn;
    }

}
