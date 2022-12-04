package com.splitscale.api.fordastore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MysqlDB implements SqlConnectable {

  @Autowired
  DBPropsLoader service;

  @Override
  public Connection getConnection() throws SQLException {

    // assign db parameters
    String url = service.getUrl();
    String user = service.getUsername();
    String password = service.getPassword();

    // create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }

}
