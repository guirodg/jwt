package com.app.data;

import com.app.model.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class DetalheUsuarioData implements UserDetails {

  private final UsuarioEntity usuarioEntity;

  public DetalheUsuarioData(UsuarioEntity usuarioEntity) {
    this.usuarioEntity = usuarioEntity;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public String getPassword() {
    return usuarioEntity.getSenha() == null ? new UsuarioEntity().getSenha() : usuarioEntity.getSenha();
  }

  @Override
  public String getUsername() {
    return usuarioEntity.getNome() == null ? new UsuarioEntity().getNome() : usuarioEntity.getNome();
  }

  @Override
  public boolean isAccountNonExpired() {
    return usuarioEntity.isNaoExpirada();
  }

  @Override
  public boolean isAccountNonLocked() {
    return usuarioEntity.isNaoBloqueada();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
