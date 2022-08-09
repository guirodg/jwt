package com.app.config.infra;

import com.app.jpa.UsuarioJPA;
import com.app.service.DetalheUsuarioServiceImpl;
import com.app.strategy.CriaTokenVerificandoSeAdmStrategy;
import com.app.strategy.CriaTokenVerificandoSeAdmStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InfraConfig {
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CriaTokenVerificandoSeAdmStrategyImpl criaTokenVerificandoSeAdmStrategy(UsuarioJPA usuarioJPA) {
    return new CriaTokenVerificandoSeAdmStrategyImpl(usuarioJPA);
  }

  @Bean
  public DetalheUsuarioServiceImpl detalheUsuarioService(UsuarioJPA usuarioJPA,
                                                         CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy) {
    return new DetalheUsuarioServiceImpl(usuarioJPA, criaTokenVerificandoSeAdmStrategy);
  }
}
