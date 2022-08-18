package com.app.usecase;

import com.app.port.CriptarSenhaUsuario;
import lombok.Getter;

class CriptarSenhaUsuarioMock implements CriptarSenhaUsuario {
  @Getter
  private boolean senhaAlterada;

  @Override
  public String executar(String senha) {
    senhaAlterada = true;
    return "321";
  }
}
