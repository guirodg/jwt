package com.app.controllers;

import com.app.entites.Usuario;
import com.app.request.CadastroRequest;
import com.app.usecase.RepositoryUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CadastroUsuarioController {
  private final RepositoryUsuario repositoryUsuario;

  @PostMapping("/cadastro")
  public ResponseEntity<Usuario> cadastroUsuario(@RequestBody CadastroRequest request) {
    final var NAO_ESTA_EXPIRADO = true;
    final var NAO_ESTA_BLOQUEADO = true;

    final var usuario = new Usuario(
        request.getNome(),
        request.getSenha(),
        NAO_ESTA_EXPIRADO,
        NAO_ESTA_BLOQUEADO
    );

    repositoryUsuario.save(usuario);
    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }
}
