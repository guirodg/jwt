package com.app.jpa;

import com.app.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJPA extends JpaRepository<UsuarioEntity, Integer> {
  Optional<UsuarioEntity> findByNome(String nome);
}
