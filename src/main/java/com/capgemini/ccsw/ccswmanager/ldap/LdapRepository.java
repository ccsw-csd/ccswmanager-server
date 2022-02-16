package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapRepository extends CrudRepository<PersonEntity, Long> {

  @Query(value = "SELECT t1.username " + "FROM person t1 " + "WHERE NOT EXISTS (SELECT NULL " + "FROM t_members t2 "
      + "WHERE t2.user_cn = t1.username and t2.group_cn = 'dlessolucionesdevon.als-val') and t1.department = 'CCSw' and active = 1", nativeQuery = true)
  List<String> comparePersonsToLdap();

  @Query(value = "SELECT t1.user_cn " + "FROM t_members t1 " + "WHERE NOT EXISTS (SELECT NULL " + "FROM person t2 "
      + "WHERE t1.user_cn = t2.username and t2.department = 'CCSw' and t2.active = 1) and t1.group_cn = 'dlessolucionesdevon.als-val'", nativeQuery = true)
  List<String> compareLdapToPersons();

}
