package com.splitscale.api.fordastore.user;

public class UserInfoImpl implements UserInfo {
  private String username;
  private String password;

  public UserInfoImpl() {
    // default
  }

  public UserInfoImpl(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

}
