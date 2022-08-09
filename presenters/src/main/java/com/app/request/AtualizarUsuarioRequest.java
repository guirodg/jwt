package com.app.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarUsuarioRequest {
  @JsonProperty("nao_expirada")
  private boolean naoExpirada;
  @JsonProperty("nao_bloqueada")
  private boolean naoBloqueada;
}
