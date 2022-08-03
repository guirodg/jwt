package com.app.config;

import com.app.service.CriptarSenhaUsuarioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfig {

  @Bean
  public CriptarSenhaUsuarioImpl criptarSenhaUsuario(PasswordEncoder passwordEncoder) {
    return new CriptarSenhaUsuarioImpl(passwordEncoder);
  }
}
