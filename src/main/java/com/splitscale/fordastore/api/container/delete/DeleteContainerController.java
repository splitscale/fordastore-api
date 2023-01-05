package com.splitscale.fordastore.api.container.delete;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.api.cors.WithCorsHeader;
import com.splitscale.shield.endpoints.container.delete.DeleteContainerEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization", allowedHeaders = "Authorization")
@RequestMapping("/container")
public class DeleteContainerController {
  DeleteContainerEndpoint endpoint;

  public DeleteContainerController(DeleteContainerEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @DeleteMapping(path = "/delete")
  public ResponseEntity<String> createContainer(@RequestBody Long containerId,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    endpoint.delete(containerId, jwsToken);

    WithCorsHeader withCorsHeaders = new WithCorsHeader();
    HttpHeaders headers = withCorsHeaders.getHeaders();

    return new ResponseEntity<>(headers, HttpStatus.OK);
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
