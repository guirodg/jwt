package com.app.controllers;

import com.app.entites.Usuario;
import com.app.request.AtualizarUsuarioRequest;
import com.app.usecase.RepositoryUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AtualizarPermissoesController {

  private final RepositoryUsuario repositoryUsuario;

  @PutMapping("/atualiza-permissoes")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> atualizaPermissoes(@RequestParam String nome,
                                              @RequestBody AtualizarUsuarioRequest request) {
    try {
      final var usuario = new Usuario();
      usuario.setNome(nome);
      usuario.setNaoBloqueada(request.isNaoBloqueada());
      usuario.setNaoExpirada(request.isNaoExpirada());
      repositoryUsuario.atualizarPermissoes(nome, usuario);
    } catch (Exception e) {
      return new ResponseEntity<>("Usuario não encontrado na base", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
