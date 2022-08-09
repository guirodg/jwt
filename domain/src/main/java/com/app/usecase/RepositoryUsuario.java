package com.app.usecase;

import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.port.CriptarSenhaUsuario;
import com.app.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class RepositoryUsuario {
  private final UsuarioRepository usuarioRepository;
  private final CriptarSenhaUsuario criptarSenhaUsuario;

  public void save(Usuario usuario) {
    if (Objects.isNull(usuario.getNome()) || Objects.isNull(usuario.getSenha())) {
      throw new DomainException("Usuario não pode ser nulo");
    }

    final var novaSenha = criptarSenhaUsuario.executar(usuario.getSenha());
    usuario.setSenha(novaSenha);
    usuarioRepository.save(usuario);
  }

  public void atualizarPermissoes(String nomeUsuario, Usuario usuarioAtualizado) {
    usuarioRepository.atualizaPermissoes(nomeUsuario, usuarioAtualizado).orElseThrow(() ->
        new DomainException("Usuario não encontrado na base de dados"));
  }
}
