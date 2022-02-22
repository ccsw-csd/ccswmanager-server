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

  @Query(value = "select t.username, t.name, t.lastname " + "from t_members m "
      + "join t_person t on m.user_cn = t.username " + "where m.group_cn = 'dlessolucionesdevon.als-val' "
      + "and not exists (select 1 from person p where p.username = m.user_cn and p.department = 'CCSw' and p.active = 1)", nativeQuery = true)
  List<LdapPerson> compareLdapToPersons();

  @Query(value = "SELECT username FROM person WHERE department = 'CCSw' and active = 1", nativeQuery = true)
  List<String> findUsernamesList();

}
