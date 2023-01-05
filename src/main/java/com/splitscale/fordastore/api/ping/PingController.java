package com.splitscale.fordastore.api.ping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.ditabys.config.DBconfig;

@RestController
@RequestMapping("/api/ping")
public class PingController {

  @GetMapping
  @CrossOrigin(origins = "*", maxAge = 3600)
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>("hello from splitscale", HttpStatus.OK);
  }

  @GetMapping("/db")
  @CrossOrigin(origins = "*", maxAge = 3600)
  public ResponseEntity<String> returnDbConnectionStatusViaPropertiesFile() {

    Properties props = new Properties();
    InputStream fileStream;

    try {
      fileStream = new FileInputStream("./store-db.properties");
      props.load(fileStream);

    } catch (FileNotFoundException e) {
      return new ResponseEntity<>(
          " If you are placing files into the resource dir, then they should be read as classpath resources: "
              + e.getMessage(),
          HttpStatus.NOT_FOUND);
    } catch (IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    DBconfig config = new DBconfig(props);

    String url = config.getUrl();
    String user = config.getUsername();
    String password = config.getPassword();

    try {
      Connection conn = DriverManager.getConnection(url, user, password);
      return new ResponseEntity<>("Connected to Database: " + conn.getMetaData(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Database connection error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
