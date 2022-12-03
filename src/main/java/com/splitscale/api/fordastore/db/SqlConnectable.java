package com.splitscale.api.fordastore.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnectable {
  public Connection getConnection() throws SQLException;
}
