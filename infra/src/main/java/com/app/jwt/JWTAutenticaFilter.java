package com.app.jwt;

import com.app.config.PropertiesConfig;
import com.app.data.DetalheUsuarioData;
import com.app.model.UsuarioEntity;
import com.app.strategy.CriaTokenVerificandoSeAdmStrategy;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JWTAutenticaFilter extends UsernamePasswordAuthenticationFilter {
  public static final int TOKEN_EXPIRACAO = 600_000;
  private final AuthenticationManager authenticationManager;
  private final CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy;
  private final PropertiesConfig propertiesConfig;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {

    try {
      final var usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntity.class);
      final var grantedAuthorities = criaTokenVerificandoSeAdmStrategy.executar(usuario.getNome());
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          usuario.getNome(),
          usuario.getSenha(),
          grantedAuthorities
      ));
    } catch (IOException e) {
      throw new RuntimeException("Falha ao autenticar " + e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {

    DetalheUsuarioData detalheUsuarioData = (DetalheUsuarioData) authResult.getPrincipal();

    final var token = JWT.create()
        .withSubject(detalheUsuarioData.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
        .sign(Algorithm.HMAC512(propertiesConfig.getToken()));

    response.getWriter().write(token);
    response.getWriter().flush();
  }
}
