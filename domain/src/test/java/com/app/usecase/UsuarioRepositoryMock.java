package com.app.usecase;

import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.port.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

class UsuarioRepositoryMock implements UsuarioRepository {

  @Getter
  private boolean usuarioSalvo;
  @Getter
  private boolean usuarioAtualizado;

  @Setter
  private boolean erroAtualizacao;

  @Override
  public void save(Usuario usuario) {
    usuarioSalvo = true;
  }

  @Override
  public void atualizaPermissoes(Usuario usuario) throws DomainException {
    if (erroAtualizacao) throw new DomainException("Erro");
    usuarioAtualizado = true;
  }
}
