package com.app.controllers;

import com.app.entites.Usuario;
import com.app.request.UsuarioRequest;
import com.app.usecase.PersistenciaUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class UsuarioController {
  private final PersistenciaUsuario persistenciaUsuario;

  @PostMapping
  public ResponseEntity<Usuario> saveClient(@RequestBody UsuarioRequest usuarioRequest) {
    final var usuario = new Usuario(usuarioRequest.getNome(),
        usuarioRequest.getSenha(), usuarioRequest.isNaoExpirada(), usuarioRequest.isNaoBloqueada());
    persistenciaUsuario.save(usuario);
    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }
}
