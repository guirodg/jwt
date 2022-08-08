package com.app.repositoryimpl;

import com.app.entites.Usuario;
import com.app.jpa.UsuarioJPA;
import com.app.model.UsuarioEntity;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final UsuarioJPA usuarioJPA;

  @Override
  public void save(Usuario usuario) {
    final var usuarioEntity = converteUsuarioParaEntityDB(usuario);
    usuarioJPA.save(usuarioEntity);
  }

  @Override
  public Optional<Usuario> atualizaPermissoes(String nomeUsuario, Usuario usuarioAtualizado) {
    final var optUsuarioEntity = usuarioJPA.findByNome(nomeUsuario);
    if (optUsuarioEntity.isPresent()) {
      final var usuarioEntity = optUsuarioEntity.get();
      usuarioEntity.setNaoBloqueada(usuarioAtualizado.isNaoBloqueada());
      usuarioEntity.setNaoExpirada(usuarioAtualizado.isNaoExpirada());

      usuarioJPA.save(usuarioEntity);

      return optUsuarioEntity.map(optUsuario -> new Usuario(
          optUsuario.getNome(),
          optUsuario.getSenha(),
          optUsuario.isNaoExpirada(),
          optUsuario.isNaoBloqueada()));
    }
    return Optional.empty();
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
