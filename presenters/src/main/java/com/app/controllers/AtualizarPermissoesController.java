package com.app.controllers;

import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.request.AtualizarUsuarioRequest;
import com.app.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AtualizarPermissoesController {

  private final UsuarioUseCase usuarioUseCase;

  @PutMapping("/atualiza-permissoes")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> atualizaPermissoes(@RequestParam String nome,
                                              @RequestBody AtualizarUsuarioRequest request) {
    try {
      final var usuario = new Usuario();
      usuario.setNome(nome);
      usuario.setNaoBloqueada(request.isNaoBloqueada());
      usuario.setNaoExpirada(request.isNaoExpirada());
      usuarioUseCase.atualizarPermissoes(usuario);
    } catch (DomainException erro) {
      return new ResponseEntity<>(erro.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
