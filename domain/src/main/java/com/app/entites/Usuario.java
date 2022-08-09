package com.app.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
  private String nome;
  private String senha;
  private boolean naoExpirada;
  private boolean naoBloqueada;
}
