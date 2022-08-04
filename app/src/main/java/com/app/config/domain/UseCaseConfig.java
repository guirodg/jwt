package com.app.config.domain;

import com.app.port.UsuarioRepository;
import com.app.usecase.PersistenciaUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public PersistenciaUsuario persistenciaUsuario(UsuarioRepository usuarioRepository) {
    return new PersistenciaUsuario(usuarioRepository);
  }
}
