package com.app.port;

import com.app.entites.Usuario;
import com.app.exception.DomainException;

public interface UsuarioRepository {
  void save(Usuario usuario);

  void atualizaPermissoes(Usuario usuario) throws DomainException;
}
