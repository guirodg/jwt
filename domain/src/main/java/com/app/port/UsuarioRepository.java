package com.app.port;

import com.app.entites.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
  void save(Usuario usuario);

  Optional<Usuario> atualizaPermissoes(String nomeUsuario, Usuario usuarioAtualizado);
}
