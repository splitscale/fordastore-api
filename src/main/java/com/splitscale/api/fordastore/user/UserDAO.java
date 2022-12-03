package com.splitscale.api.fordastore.user;

public interface UserDAO {
  // finds the user using username
  void createUser(String username, String password);

  void readUser(String username);

  void updateUser(String username, String password);

  void deleteUser(String username, String password);
}
