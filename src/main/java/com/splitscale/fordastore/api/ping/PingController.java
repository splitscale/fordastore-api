package com.splitscale.fordastore.api.ping;

import java.sql.DriverManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.ditabys.driver.StoreDbDriver;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ping")
public class PingController {

  @GetMapping
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>("hello", HttpStatus.OK);
  }

  @GetMapping("/db")
  public ResponseEntity<String> returnDbConnectionStatus() {
    StoreDbDriver storeDbDriver = new StoreDbDriver();

    try {
      storeDbDriver.getConnection();
      return new ResponseEntity<>("Connected to Database", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Database connection error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<String> ping(@RequestBody String challenge) throws IllegalArgumentException {

    if (challenge.equals("hi")) {
      return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    throw new IllegalArgumentException("should say hi");
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
