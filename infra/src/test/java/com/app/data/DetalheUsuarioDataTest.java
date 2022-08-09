package com.app.data;


import com.app.model.UsuarioEntity;
import org.junit.jupiter.api.Test;

class DetalheUsuarioDataTest {

  final DetalheUsuarioData makeSut(UsuarioEntity usuarioEntity) {
    return new DetalheUsuarioData(usuarioEntity);
  }

  @Test
  void testeUm() {
    final var usuarioEntity = new UsuarioEntity();
    final var detalheUsuarioData = makeSut(usuarioEntity);
    detalheUsuarioData.getUsername();
  }

}