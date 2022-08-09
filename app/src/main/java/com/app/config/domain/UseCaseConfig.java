package com.app.config.domain;

import com.app.port.CriptarSenhaUsuario;
import com.app.port.UsuarioRepository;
import com.app.usecase.RepositoryUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public RepositoryUsuario persistenciaUsuario(UsuarioRepository usuarioRepository,
                                               CriptarSenhaUsuario criptarSenhaUsuario) {
    return new RepositoryUsuario(usuarioRepository, criptarSenhaUsuario);
  }
}
