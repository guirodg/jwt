package com.app.jwt;

import com.app.data.DetalheUsuarioData;
import com.app.model.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticaFilter extends UsernamePasswordAuthenticationFilter {
  public static final int TOKEN_EXPIRACAO = 600_000;
  public static final String TOKEN_SENHA = "senha_secreta";

  private final AuthenticationManager authenticationManager;

  public JWTAutenticaFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {

    try {
      final var usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntity.class);
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          usuario.getNome(),
          usuario.getSenha(),
          new ArrayList<>()
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
        .sign(Algorithm.HMAC512(TOKEN_SENHA));

    response.getWriter().write(token);
    response.getWriter().flush();
  }
}
