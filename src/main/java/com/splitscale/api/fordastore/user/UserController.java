package com.splitscale.api.fordastore.user;

public interface UserController {

  public UserInfo getUser(String JSONgetPayload);

  public void addUser(String JSONpostPayload);

  public void deleteUser(String JSONpostPayload);

  public void updateUser(String JSONpostPayload);

}
