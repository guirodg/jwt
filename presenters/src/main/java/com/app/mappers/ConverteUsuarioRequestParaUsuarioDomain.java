package com.app.mappers;

import com.app.entites.Usuario;
import com.app.request.UsuarioRequest;
import org.springframework.stereotype.Component;

@Component
public class ConverteUsuarioRequestParaUsuarioDomain {
  public Usuario executar(UsuarioRequest request) {
    return new Usuario(request.getNome(), request.getSenha());
  }
}
