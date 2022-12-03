package com.splitscale.api.fordastore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class MysqlDB implements SqlConnectable {

  @Override
  public Connection getConnection() throws SQLException {

    // load the properties file

    // assign db parameters
    String url = "jdbc:mysql://localhost:3307/fordaStore?allowPublicKeyRetrieval=true&useSSL=false";
    String user = "root";
    String password = "splitscale";

    // create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }

}
