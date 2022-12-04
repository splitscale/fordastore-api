package com.splitscale.api.fordastore.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitscale.api.fordastore.db.SqlConnectable;

@Service
public class UserDAOimpl implements UserDAO {
  private SqlConnectable db;

  @Autowired
  public UserDAOimpl(SqlConnectable db) {
    this.db = db;
  }

  @Override
  public void createUser(String username, String password) {
    String update = "INSERT INTO User (username, userPassword) VALUES ('" + username + "', '" + password + "');";

    Connection connection;
    try {
      connection = db.getConnection();
      Statement statement = connection.createStatement();
      int result = statement.executeUpdate(update);

      System.out.println(result);

      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public UserInfo readUser(String username) {
    String query = "SELECT username, userPassword FROM User u WHERE u.username = " + username + ";";
    UserInfo user = new UserInfoImpl();

    try {
      Connection connection = db.getConnection();
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);

      while (rs.next()) {
        String resUsername = rs.getString("username");
        String resPassword = rs.getString("userPassword");

        user.setUsername(resUsername);
        user.setPassword(resPassword);
      }

      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }

  @Override
  public void updateUser(String username, String password) {
    //
  }

  @Override
  public void deleteUser(String username, String password) {
    //
  }

}
