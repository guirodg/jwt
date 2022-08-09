package com.app.service;

import com.app.port.CriptarSenhaUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriptarSenhaUsuarioImpl implements CriptarSenhaUsuario {

  private final PasswordEncoder passwordEncoder;

  public CriptarSenhaUsuarioImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String executar(String senha) {
    return passwordEncoder.encode(senha);
  }
}
