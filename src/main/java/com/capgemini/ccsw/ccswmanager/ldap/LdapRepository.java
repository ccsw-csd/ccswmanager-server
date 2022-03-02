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

	@Query(value = "select t1.username, t1.name, t1.lastname " //
	         + "from person t1 where not exists (select null " //
	         + "from t_members t2 where t2.user_cn = t1.username " //
	         + "and t2.group_cn = 'dlesccsw' and :tipoLista = '0'or t2.group_cn = 'dlesccsw.becarios' and :tipoLista = '1') and t1.department = 'CCSw'"
	         + "and (t1.grade is not null and t1.grade != '' and :tipoLista ='0') or (t1.grade is null or t1.grade=''and :tipoLista ='1') and active = 1 " //
	         + "ORDER BY t1.lastname, t1.name", nativeQuery = true)
  List<LdapPerson> comparePersonsToLdap(@Param("tipoLista") String tipoLista);

	@Query(value = "select t.username, t.name, t.lastname " //
	         + "from t_members m join t_person t on m.user_cn = t.username " //
	         + "where m.group_cn = 'dlesccsw' and :tipoLista = '0' or m.group_cn = 'dlesccsw.becarios' and :tipoLista = '1'"
	         + "and (t.grade is null or t.grade='' and :tipoLista ='1' or t.grade is not null and t.grade!='' and :tipoLista='0')"
	         + "and not exists (select 1 from person p where p.username = m.user_cn and p.department = 'CCSw' "
	         + "and p.active = 1) ORDER BY t.lastname, t.name", nativeQuery = true)//
	         
  List<LdapPerson> compareLdapToPersons(@Param("tipoLista") String tipoLista);

   @Query(value = "SELECT username FROM person WHERE department = 'CCSw'"
   		+ "and (grade is null or grade='' and :grade = false or grade is not null and grade !='' and :grade=true) "
   		+ "and active = 1", nativeQuery = true)
   List<String> findUsernamesList(@Param("grade") boolean grade);

}
