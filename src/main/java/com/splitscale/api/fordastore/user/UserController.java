package com.splitscale.api.fordastore.user;

public interface UserController {

  public UserInfo getUser(String JSONgetPayload);

  public void addUser(UserInfoImpl userInfo);

  public void deleteUser(UserInfoImpl userInfo);

  public void updateUser(UserInfoImpl userInfo);

}
