package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapRepository extends CrudRepository<PersonEntity, Long> {

  @Query(value = "SELECT t1.username, t1.name, t1.lastname " + "FROM person t1 " + "WHERE NOT EXISTS (SELECT NULL "
      + "FROM t_members t2 "
      + "WHERE t2.user_cn = t1.username and t2.group_cn = 'dlessolucionesdevon.als-val') and t1.department = 'CCSw' and active = 1", nativeQuery = true)
  List<LdapPerson> comparePersonsToLdap();

  @Query(value = "select t3.username, t3.name, t3.lastname from t_person t3 where exists "
      + "(SELECT t1.user_cn FROM t_members t1 WHERE NOT EXISTS (SELECT NULL " + "FROM person t2 "
      + "WHERE t1.user_cn = t2.username and t2.department = 'CCSw' and t2.active = 1) and t1.group_cn = 'dlessolucionesdevon.als-val' "
      + "and t3.username = t1.user_cn)", nativeQuery = true)
  List<LdapPerson> compareLdapToPersons();

}
