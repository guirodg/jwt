package com.app.strategy;

import com.app.jpa.UsuarioJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

@RequiredArgsConstructor
public class CriaTokenVerificandoSeAdmStrategyImpl implements CriaTokenVerificandoSeAdmStrategy {
  private final UsuarioJPA usuarioJPA;

  private String nomeUsuario;

  @Override
  public List<GrantedAuthority> executar(String nome) {
    final var usuario = usuarioJPA.findByNome(nome).orElseThrow(() ->
        new RuntimeException("Usuario não encontrado na base"));
    salvaNomeUsuario(nome);
    if (usuario.isAdministrator())
      return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    else
      return AuthorityUtils.createAuthorityList("ROLE_USER");
  }

  private void salvaNomeUsuario(String nome) {
    this.nomeUsuario = nome;
  }

  @Override
  public String getNomeUsuario() {
    return nomeUsuario;
  }
}
