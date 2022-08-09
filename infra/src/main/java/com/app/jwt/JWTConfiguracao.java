package com.app.jwt;

import com.app.service.DetalheUsuarioServiceImpl;
import com.app.strategy.CriaTokenVerificandoSeAdmStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

  private final DetalheUsuarioServiceImpl detalheUsuarioService;
  private final PasswordEncoder passwordEncoder;

  private final CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(detalheUsuarioService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/api/cadastro").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/atualiza-permissoes").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAutenticaFilter(authenticationManager(), criaTokenVerificandoSeAdmStrategy))
        .addFilter(new JWTValidaFilter(authenticationManager(), criaTokenVerificandoSeAdmStrategy))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final var corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
