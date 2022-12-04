package com.splitscale.api.fordastore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.splitscale.api.fordastore.db.DBPropsLoader;

@EnableConfigurationProperties
public class DBpropsLoaderImplTests {

  @Autowired
  DBPropsLoader loader;

  @Test
  @DisplayName("should get props in props loader")
  void ShouldGetDBProperties() {

    assertNotNull(loader.getUrl());
    assertNotNull(loader.getUsername());
    assertNotNull(loader.getPassword());
  }

}
