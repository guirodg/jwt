package com.app.config.data;

import com.app.port.CriptarSenhaUsuario;
import com.app.jpa.UsuarioJPA;
import com.app.repositoryimpl.UsuarioRepositoryImpl;
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

  @Bean
  public UsuarioRepositoryImpl usuarioRepository(UsuarioJPA usuarioJPA,
                                                 CriptarSenhaUsuario criptarSenhaUsuario) {
    return new UsuarioRepositoryImpl(usuarioJPA, criptarSenhaUsuario);
  }
}
