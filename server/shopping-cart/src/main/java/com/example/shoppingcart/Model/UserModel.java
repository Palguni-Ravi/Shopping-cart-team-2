package com.example.shoppingcart.Model;

import java.util.Collection;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "users")
public class UserModel implements UserDetails {

  private String name;
  private String email;
  private String password;
  private CartItem cartItem;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public CartItem getCartIem() {
    return cartItem;
  }

  public void setCartIem(CartItem cartItem) {
    this.cartItem = cartItem;
  }

  public UserModel(String name, String email, String password) {
    super();
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public UserModel() {}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
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