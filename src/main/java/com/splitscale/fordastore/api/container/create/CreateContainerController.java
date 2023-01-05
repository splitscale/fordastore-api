package com.splitscale.fordastore.api.container.create;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.shield.endpoints.container.create.CreateContainerEndpoint;

@RestController
@RequestMapping("/api/containers")
public class CreateContainerController {
  CreateContainerEndpoint endpoint;

  public CreateContainerController(CreateContainerEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PostMapping
  @CrossOrigin
  public ResponseEntity<Container> createContainer(@RequestBody ContainerRequest containerRequest,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    Container container = endpoint.create(containerRequest, jwsToken);

    return new ResponseEntity<Container>(container, HttpStatus.OK);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
