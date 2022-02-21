package com.capgemini.ccsw.ccswmanager.usuario;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.usuario.model.Usuario;

public interface UsuarioService {
  
  List<Usuario> findUsuario();
}
