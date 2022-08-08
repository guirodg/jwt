package com.app.service;

import com.app.data.DetalheUsuarioData;
import com.app.jpa.UsuarioJPA;
import com.app.strategy.CriaTokenVerificandoSeAdmStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DetalheUsuarioServiceImpl implements UserDetailsService {

  private final UsuarioJPA repository;
  private final CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy;

  public DetalheUsuarioServiceImpl(UsuarioJPA repository, CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy) {
    this.repository = repository;
    this.criaTokenVerificandoSeAdmStrategy = criaTokenVerificandoSeAdmStrategy;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var usuario = repository.findByNome(username);

    if (usuario.isEmpty()) throw new UsernameNotFoundException("Usuario [" + username + "] não encontrado");

    final var detalheUsuarioData = new DetalheUsuarioData(usuario.get());
    final var grantedAuthorities = criaTokenVerificandoSeAdmStrategy.executar(username);
    detalheUsuarioData.setGrantedAuthorities(grantedAuthorities);
    return detalheUsuarioData;
  }
}
