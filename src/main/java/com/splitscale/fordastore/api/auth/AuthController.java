package com.splitscale.fordastore.api.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.splitscale.shield.endpoints.RegisterEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private RegisterEndpoint endpoint;

  @ResponseBody
  @PostMapping(path = "/register")
  public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
    // Create an instance of the HttpHeaders class
    HttpHeaders headers = new HttpHeaders();

    try {
      final String jwt = endpoint.register(userRequest);

      // Add a header to the headers collection
      headers.add("Authorization", jwt);

      return new ResponseEntity<String>("success", headers, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
