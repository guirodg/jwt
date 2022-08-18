package com.app.repositoryimpl;

import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.jpa.UsuarioJPA;
import com.app.model.UsuarioEntity;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final UsuarioJPA usuarioJPA;

  @Override
  public void save(Usuario usuario) {
    final var usuarioEntity = UsuarioEntity.builder()
        .nome(usuario.getNome())
        .senha(usuario.getSenha())
        .naoBloqueada(usuario.isNaoBloqueada())
        .naoExpirada(usuario.isNaoExpirada())
        .build();
    usuarioJPA.save(usuarioEntity);
  }

  @Override
  public void atualizaPermissoes(Usuario usuario) throws DomainException {
    final var usuarioEntity = usuarioJPA.findByNome(usuario.getNome())
        .orElseThrow(() -> new DomainException("Usuario não encontrado"));

    usuarioEntity.setNaoBloqueada(usuario.isNaoBloqueada());
    usuarioEntity.setNaoExpirada(usuario.isNaoExpirada());
    usuarioJPA.save(usuarioEntity);
  }
}
