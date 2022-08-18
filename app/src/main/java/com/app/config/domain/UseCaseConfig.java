package com.app.config.domain;

import com.app.port.CriptarSenhaUsuario;
import com.app.port.UsuarioRepository;
import com.app.usecase.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public UsuarioUseCase persistenciaUsuario(UsuarioRepository usuarioRepository,
                                            CriptarSenhaUsuario criptarSenhaUsuario) {
    return new UsuarioUseCase(usuarioRepository, criptarSenhaUsuario);
  }
}
