package com.capgemini.ccsw.ccswmanager.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.ccswmanager.usuario.model.UsuarioEntity;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
  
  @Query(value = "select u.id, p.username, u.role, p.name, p.lastname from person p INNER JOIN user u ON p.username = u.username", nativeQuery = true)
  List<UsuarioEntity> findUsuario();

}