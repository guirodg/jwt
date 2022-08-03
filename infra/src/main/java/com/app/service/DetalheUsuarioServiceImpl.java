package com.app.service;

import com.app.jpa.UsuarioJPA;
import com.app.data.DetalheUsuarioData;
import com.app.port.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

  private final UsuarioJPA repository;

  public DetalheUsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioJPA repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var usuario = repository.findByNome(username);
    if (usuario.isEmpty())
      throw new UsernameNotFoundException("Usuario [" + username + "] não encontrado");

    return new DetalheUsuarioData(usuario.get());
  }
}
