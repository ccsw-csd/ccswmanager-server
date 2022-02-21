package com.capgemini.ccsw.ccswmanager.usuario;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.usuario.model.Usuario;


@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private BeanMapper beanMapper;
  
  @Autowired
  UsuarioRepository usuarioRepository;

  /**
   * {@inheritDoc}
   */

	@Override
	public List<Usuario> findUsuario() {
		
		return this.beanMapper.mapList(this.usuarioRepository.findUsuario(), Usuario.class);
	}

}