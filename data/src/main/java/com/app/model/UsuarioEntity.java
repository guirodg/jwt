package com.app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String nome;
  private String senha;
  @Column(name = "nao_expirada")
  private boolean naoExpirada;
  @Column(name = "nao_bloqueada")
  private boolean naoBloqueada;
  private boolean administrator;
}
