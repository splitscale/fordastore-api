package com.splitscale.fordastore.api.auth;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.endpoints.LoginEndpoint;
import com.splitscale.shield.endpoints.RegisterEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization")
@RequestMapping("/auth")
public class AuthController {
  private RegisterEndpoint registerEndpoint;
  private LoginEndpoint loginEndpoint;

  public AuthController(RegisterEndpoint registerEndpoint, LoginEndpoint loginEndpoint) {
    this.registerEndpoint = registerEndpoint;
    this.loginEndpoint = loginEndpoint;
  }

  @ResponseBody
  @PostMapping(path = "/register")
  public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
    try {
      registerEndpoint.register(userRequest);

      System.out.println("POST register: " + HttpStatus.OK.toString());

      return new ResponseEntity<String>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      System.out.println("POST register: " + HttpStatus.BAD_REQUEST.toString());
      System.out.println(e.getMessage());

      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      System.out.println("POST register: " + HttpStatus.INTERNAL_SERVER_ERROR.toString());
      System.out.println(e.getMessage());

      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // login

  @ResponseBody
  @PostMapping(path = "/login")
  public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
    // Create an instance of the HttpHeaders class
    HttpHeaders headers = new HttpHeaders();

    try {
      final String jwt = loginEndpoint.login(userRequest);

      // Add a header to the headers collection
      headers.add("Authorization", "Bearer " + jwt);

      System.out.println("POST login: " + HttpStatus.OK.toString());
      System.out.println("Authorization: " + jwt);

      return new ResponseEntity<String>("success", headers, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      System.out.println("POST login: " + HttpStatus.BAD_REQUEST.toString());
      System.out.println(e.getMessage());

      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      System.out.println("POST login: " + HttpStatus.INTERNAL_SERVER_ERROR.toString());
      System.out.println(e.getMessage());

      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
