package com.app.repositoryimpl;

import com.app.entites.Usuario;
import com.app.jpa.UsuarioJPA;
import com.app.model.UsuarioEntity;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final UsuarioJPA usuarioJPA;

  @Override
  public void save(Usuario usuario) {
    final var usuarioEntity = converteUsuarioParaEntityDB(usuario);
    usuarioJPA.save(usuarioEntity);
  }

  private UsuarioEntity converteUsuarioParaEntityDB(Usuario usuario) {
    final var usuarioEntity = new UsuarioEntity();
    usuarioEntity.setNome(usuario.getNome());
    usuarioEntity.setSenha(usuario.getSenha());
    usuarioEntity.setNaoBloqueada(usuario.isNaoBloqueada());
    usuarioEntity.setNaoExpirada(usuario.isNaoExpirada());
    return usuarioEntity;
  }
}
