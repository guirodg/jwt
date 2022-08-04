package com.app.repositoryimpl;


import com.app.port.CriptarSenhaUsuario;
import com.app.entites.Usuario;
import com.app.jpa.UsuarioJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UsuarioRepositoryImplTest {
  final UsuarioRepositoryImpl makeSut(CriptarSenhaUsuarioMock criptarMock) {
    final var jpaMock = mock(UsuarioJPA.class);
    return new UsuarioRepositoryImpl(jpaMock, criptarMock);
  }

  @Test
  @DisplayName("Deve garantir que está chamando minha porta de criptar senha")
  void testeUm() {
    final var criptarSenhaUsuarioMock = new CriptarSenhaUsuarioMock();
    final var sut = makeSut(criptarSenhaUsuarioMock);
    sut.save(new Usuario("Joao", "123"));

    assertEquals(1, criptarSenhaUsuarioMock.contador);
  }


}

class CriptarSenhaUsuarioMock implements CriptarSenhaUsuario {
  public int contador;

  @Override
  public String executar(String senha) {
    contador++;
    return null;
  }
}