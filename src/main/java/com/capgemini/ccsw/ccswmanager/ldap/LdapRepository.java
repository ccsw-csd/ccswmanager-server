package com.capgemini.ccsw.ccswmanager.ldap;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.ccswmanager.ldap.model.LdapPerson;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;

/**
 * TODO asolerpa This type ...
 *
 */
public interface LdapRepository extends CrudRepository<PersonEntity, Long> {

	@Query(value = "SELECT t1.username, t1.name, t1.lastname " //
	         + "FROM person t1 " + "WHERE NOT EXISTS (SELECT NULL " //
	         + "FROM t_members t2 " //
	         + "WHERE t2.user_cn = t1.username and t2.group_cn = 'dlesccsw') and (t1.grade!='' and t1.grade IS not null) and t1.department = 'CCSw' and active = 1 " //
	         + "ORDER BY t1.lastname, t1.name", nativeQuery = true)
  List<LdapPerson> comparePersonsToLdap();

	 @Query(value = "select t.username, t.name, t.lastname " //
	         + "from t_members m " //
	         + "join t_person t on m.user_cn = t.username " //
	         + "where m.group_cn = 'dlesccsw' " //
	         + "and not exists (select 1 from person p where p.username = m.user_cn and (p.grade!='' and p.grade is not null)  and p.department = 'CCSw' and p.active = 1) " //
	         + "ORDER BY t.lastname, t.name", nativeQuery = true)         
  List<LdapPerson> compareLdapToPersons();
	 
	 @Query(value = "SELECT t1.username, t1.name, t1.lastname " //
	         + "FROM person t1 " + "WHERE NOT EXISTS (SELECT NULL " //
	         + "FROM t_members t2 " //
	         + "WHERE t2.user_cn = t1.username and t2.group_cn = 'dlesccsw.becarios') and (t1.grade='' OR t1.grade IS null )  and t1.department = 'CCSw' and active = 1 " //
	         + "ORDER BY t1.lastname, t1.name", nativeQuery = true)
  List<LdapPerson> comparePersonsToLdapScholars();

	 @Query(value = "select t.username, t.name, t.lastname " //
	         + "from t_members m " //
	         + "join t_person t on m.user_cn = t.username " //
	         + "where m.group_cn = 'dlesccsw.becarios' " //
	         + "and not exists (select 1 from person p where p.username = m.user_cn and (p.grade='' OR p.grade IS null) and p.department = 'CCSw' and p.active = 1) " //
	         + "ORDER BY t.lastname, t.name", nativeQuery = true)         
  List<LdapPerson> compareLdapToPersonsScholars();

   @Query(value = "SELECT username FROM person WHERE department = 'CCSw'"
   		+ "and (grade is null or grade='' and :contract = false or grade is not null and grade !='' and :contract=true) "
   		+ "and active = 1", nativeQuery = true)
   List<String> findUsernamesList(@Param("contract") boolean contract);

}
