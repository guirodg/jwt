package com.app.jwt;

import com.app.strategy.CriaTokenVerificandoSeAdmStrategy;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JWTValidaFilter extends BasicAuthenticationFilter {
  public static final String HEADER_ATRIBUTO = "Authorization";
  public static final String ATRIBUTO_PREFIX = "Bearer ";

  private final CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy;

  public JWTValidaFilter(AuthenticationManager authenticationManager,
                         CriaTokenVerificandoSeAdmStrategy criaTokenVerificandoSeAdmStrategy) {
    super(authenticationManager);
    this.criaTokenVerificandoSeAdmStrategy = criaTokenVerificandoSeAdmStrategy;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {
    final var atributo = request.getHeader(HEADER_ATRIBUTO);
    if (Objects.isNull(atributo)) {
      chain.doFilter(request, response);
      return;
    }

    if (!atributo.startsWith(ATRIBUTO_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    final var token = atributo.replace(ATRIBUTO_PREFIX, "");

    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
    final var usuario = JWT.require(Algorithm.HMAC512(JWTAutenticaFilter.TOKEN_SENHA))
        .build().verify(token).getSubject();

    if (Objects.isNull(usuario)) return null;
    final var nomeUsuario = criaTokenVerificandoSeAdmStrategy.getNomeUsuario();
    final var grantedAuthorities = criaTokenVerificandoSeAdmStrategy.executar(nomeUsuario);

    return new UsernamePasswordAuthenticationToken(usuario, null, grantedAuthorities);
  }
}
