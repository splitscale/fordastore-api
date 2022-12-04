package com.splitscale.api.fordastore.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBPropsLoaderImpl implements DBPropsLoader {
  @Value("${mysql.url}")
  private String url;
  @Value("${mysql.username}")
  private String username;
  @Value("${mysql.password}")
  private String password;

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

}
