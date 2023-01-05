package com.splitscale.fordastore.api.url.delete;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.api.cors.WithCorsHeader;
import com.splitscale.shield.endpoints.url.delete.DeleteUrlEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization", allowedHeaders = "Authorization")
@RequestMapping("/api/urls")
public class DeleteUrlController {
  DeleteUrlEndpoint endpoint;

  public DeleteUrlController(DeleteUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @DeleteMapping(path = "/{urlId}")
  public ResponseEntity<String> deleteUrl(@PathVariable Long urlId,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    endpoint.delete(urlId, jwsToken);

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
