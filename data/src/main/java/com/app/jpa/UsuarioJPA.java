package com.app.jpa;

import com.app.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJPA extends JpaRepository<UsuarioEntity, Integer> {
  Optional<UsuarioEntity> findByNome(String nome);
}
