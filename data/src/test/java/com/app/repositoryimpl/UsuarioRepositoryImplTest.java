package com.app.repositoryimpl;


import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.jpa.UsuarioJPA;
import com.app.model.UsuarioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class UsuarioRepositoryImplTest {
  final UsuarioRepositoryImpl usuarioRepository(UsuarioJPA usuarioJPA) {
    return new UsuarioRepositoryImpl(usuarioJPA);
  }

  @Test
  @DisplayName("Deve simular que está salvando usuario")
  void testePrimeiro() {
    final var usuarioJPA = mock(UsuarioJPA.class);
    final var usuarioRepository = usuarioRepository(usuarioJPA);
    final var joao = new Usuario("João", "123", true, true);
    usuarioRepository.save(joao);
    verify(usuarioJPA).save(any());
  }

  @Test
  @DisplayName("Deve simular que está atualizando usuario")
  void testeSegundo() {
    final var usuarioJPA = mock(UsuarioJPA.class);
    final var usuarioRepository = usuarioRepository(usuarioJPA);
    final var joaoEntity = UsuarioEntity.builder().nome("João").naoBloqueada(false).naoExpirada(false).build();
    doReturn(Optional.of(joaoEntity)).when(usuarioJPA).findByNome(any());

    final var joao = new Usuario("João", "123", true, true);
    usuarioRepository.atualizaPermissoes(joao);

    verify(usuarioJPA).findByNome(any());
  }

  @Test
  @DisplayName("Deve lançar erro quando Optional for vazio")
  void testeTerceiro() {
    final var usuarioJPA = mock(UsuarioJPA.class);
    final var usuarioRepository = usuarioRepository(usuarioJPA);
    doReturn(Optional.empty()).when(usuarioJPA).findByNome(any());

    final var joao = new Usuario("João", "123", true, true);

    assertThrows(DomainException.class, () -> usuarioRepository.atualizaPermissoes(joao));
  }
}