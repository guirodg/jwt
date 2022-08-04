package com.app.repositoryimpl;

import com.app.port.CriptarSenhaUsuario;
import com.app.entites.Usuario;
import com.app.jpa.UsuarioJPA;
import com.app.model.UsuarioEntity;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

  private final UsuarioJPA usuarioJPA;
  private final CriptarSenhaUsuario criptarSenhaUsuario;

  @Override
  public void save(Usuario usuario) {
    final var senha = criptarSenhaUsuario.executar(usuario.getSenha());
    usuario.setSenha(senha);
    usuarioJPA.save(converteUsuarioParaEntityDB(usuario));
  }

  private UsuarioEntity converteUsuarioParaEntityDB(Usuario usuario) {
    final var usuarioEntity = new UsuarioEntity();
    usuarioEntity.setNome(usuario.getNome());
    usuarioEntity.setSenha(usuario.getSenha());
    return usuarioEntity;
  }
}
