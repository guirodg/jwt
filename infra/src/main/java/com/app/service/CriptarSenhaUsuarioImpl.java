package com.app.service;

import com.app.CriptarSenhaUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
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
