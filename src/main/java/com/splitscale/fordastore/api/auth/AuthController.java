package com.splitscale.fordastore.api.auth;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.api.cors.WithCorsHeader;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.UserResponse;
import com.splitscale.shield.endpoints.auth.LoginEndpoint;
import com.splitscale.shield.endpoints.auth.RegisterEndpoint;
import com.splitscale.shield.response.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private RegisterEndpoint registerEndpoint;
  private LoginEndpoint loginEndpoint;

  public AuthController(RegisterEndpoint registerEndpoint, LoginEndpoint loginEndpoint) {
    this.registerEndpoint = registerEndpoint;
    this.loginEndpoint = loginEndpoint;
  }

  @ResponseBody
  @PostMapping(path = "/register")
  public ResponseEntity<String> register(@RequestBody UserRequest userRequest)
      throws IllegalArgumentException, IOException {

    registerEndpoint.register(userRequest);

    WithCorsHeader withCorsHeaders = new WithCorsHeader();
    HttpHeaders headers = withCorsHeaders.getHeaders();

    return new ResponseEntity<String>(headers, HttpStatus.OK);
  }

  @ResponseBody
  @CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization")
  @PostMapping(path = "/login")
  public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest)
      throws IllegalArgumentException, IOException {

    WithCorsHeader withCorsHeaders = new WithCorsHeader();
    HttpHeaders headers = withCorsHeaders.getHeaders();

    LoginResponse loginResponse = loginEndpoint.login(userRequest);

    headers.add("Authorization", loginResponse.getJws());

    return new ResponseEntity<UserResponse>(loginResponse.getUserResponse(), headers, HttpStatus.OK);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
