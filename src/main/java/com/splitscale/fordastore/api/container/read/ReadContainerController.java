package com.splitscale.fordastore.api.container.read;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.shield.endpoints.container.read.ReadContainerEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/container")
public class ReadContainerController {
  ReadContainerEndpoint endpoint;

  public ReadContainerController(ReadContainerEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @GetMapping(path = "/{containerId}")
  public ResponseEntity<Container> readContainer(@PathVariable Long containerId,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    Container container = endpoint.readByContainerId(containerId, jwsToken);

    return new ResponseEntity<>(container, HttpStatus.OK);

  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
