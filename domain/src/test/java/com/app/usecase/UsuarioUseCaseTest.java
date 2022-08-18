package com.app.usecase;


import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.port.CriptarSenhaUsuario;
import com.app.port.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioUseCaseTest {
  final UsuarioUseCase useCaseMock(UsuarioRepositoryMock usuarioRepositoryMock,
                                   CriptarSenhaUsuarioMock criptarSenhaUsuarioMock) {
    if (Objects.isNull(criptarSenhaUsuarioMock)) {
      final var criptarSenhaUsuario = new CriptarSenhaUsuarioMock();
      return new UsuarioUseCase(usuarioRepositoryMock, criptarSenhaUsuario);
    } else {
      return new UsuarioUseCase(usuarioRepositoryMock, criptarSenhaUsuarioMock);
    }
  }

  @Test
  @DisplayName("Deve salvar um usuario sem erro")
  void testePrimeiro() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var usuarioUseCase = useCaseMock(usuarioRepositoryMock, null);
    final var joao = new Usuario("João", "123", true, true);
    usuarioUseCase.save(joao);
    assertTrue(usuarioRepositoryMock.isUsuarioSalvo());
  }

  @Test
  @DisplayName("Deve lançar erro DomainException")
  void testeSegundo() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var usuarioUseCase = useCaseMock(usuarioRepositoryMock, null);
    final var joao = new Usuario();
    assertThrows(DomainException.class, () -> usuarioUseCase.save(joao));
  }

  @Test
  @DisplayName("Deve alterar a senha do usuario criptografando")
  void testeTerceiro() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var criptarSenhaUsuarioMock = new CriptarSenhaUsuarioMock();
    final var usuarioUseCase = useCaseMock(usuarioRepositoryMock, criptarSenhaUsuarioMock);
    final var joao = new Usuario("João", "123", true, true);
    usuarioUseCase.save(joao);
    assertTrue(criptarSenhaUsuarioMock.isSenhaAlterada());
  }

  @Test
  @DisplayName("Deve atualizar usuario")
  void testeQuarto() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var criptarSenhaUsuarioMock = new CriptarSenhaUsuarioMock();
    final var usuarioUseCase = useCaseMock(usuarioRepositoryMock, criptarSenhaUsuarioMock);
    final var joao = new Usuario("João", "123", false, false);
    usuarioUseCase.atualizarPermissoes(joao);
    assertTrue(usuarioRepositoryMock.isUsuarioAtualizado());
  }

  @Test
  @DisplayName("Deve lançar erro quando não conseguir atualizar usuario")
  void testeQuinto() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var criptarSenhaUsuarioMock = new CriptarSenhaUsuarioMock();
    final var usuarioUseCase = useCaseMock(usuarioRepositoryMock, criptarSenhaUsuarioMock);
    final var joao = new Usuario("João", "123", false, false);
    usuarioRepositoryMock.setErroAtualizacao(true);
    assertThrows(DomainException.class, () -> usuarioUseCase.atualizarPermissoes(joao));
  }
}

