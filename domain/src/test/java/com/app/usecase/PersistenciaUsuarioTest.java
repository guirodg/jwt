package com.app.usecase;


import com.app.entites.Usuario;
import com.app.exception.DomainException;
import com.app.port.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersistenciaUsuarioTest {

  final PersistenciaUsuario makeSut(UsuarioRepositoryMock mock) {
    return new PersistenciaUsuario(mock);
  }

  @Test
  @DisplayName("Deve garantir que está chamando meu repository")
  void testeUm() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var sut = makeSut(usuarioRepositoryMock);
    final var joao = new Usuario("João", "123");
    sut.save(joao);

    assertEquals(1, usuarioRepositoryMock.contador);
  }

  @Test
  @DisplayName("Deve garantir que lança exceção, quando objeto Usuario for nulo")
  void testeDois() {
    final var usuarioRepositoryMock = new UsuarioRepositoryMock();
    final var sut = makeSut(usuarioRepositoryMock);
    final var joao = new Usuario(null, null);

    assertThrows(DomainException.class, () -> sut.save(joao));
  }

}

class UsuarioRepositoryMock implements UsuarioRepository {

  public int contador;

  @Override
  public void save(Usuario usuario) {
    contador++;
  }
}
