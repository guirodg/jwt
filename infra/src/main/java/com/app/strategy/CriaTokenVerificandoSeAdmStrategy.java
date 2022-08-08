package com.app.strategy;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface CriaTokenVerificandoSeAdmStrategy {

  List<GrantedAuthority> executar(String nome);

  String getNomeUsuario();
}
