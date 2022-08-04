package com.app.usecase;

import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class PersistenciaUsuario {
  private final UsuarioRepository usuarioRepository;

  public void save(Usuario usuario) {
    if (Objects.isNull(usuario.getNome()) || Objects.isNull(usuario.getSenha()))
      throw new DomainException("Usuario não pode ser nulo");
    usuarioRepository.save(usuario);
  }
}
