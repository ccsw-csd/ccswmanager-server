package com.capgemini.ccsw.ccswmanager.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.ccswmanager.usuario.model.Usuario;

@RequestMapping(value = "/usuario/")
@RestController
public class UsuarioController {

	  @Autowired
	  private UsuarioService usuarioService;

	  @RequestMapping(path = "/", method = RequestMethod.GET)
	  public List<Usuario> findUsuario() {

	    return this.usuarioService.findUsuario();

	  }
}