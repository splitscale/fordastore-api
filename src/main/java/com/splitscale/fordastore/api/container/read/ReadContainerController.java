package com.splitscale.fordastore.api.container.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.api.cors.WithCorsHeader;
import com.splitscale.fordastore.core.container.ContainerResponse;
import com.splitscale.shield.endpoints.container.read.ReadContainerEndpoint;

import io.jsonwebtoken.Header;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization", allowedHeaders = "Authorization")
@RequestMapping("/api/containers")
public class ReadContainerController {
  ReadContainerEndpoint endpoint;

  public ReadContainerController(ReadContainerEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @GetMapping(path = "/{containerId}")
  public ResponseEntity<ContainerResponse> readContainer(@PathVariable Long containerId,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    ContainerResponse containerResponse = endpoint.readByContainerId(containerId, jwsToken);

    WithCorsHeader withCorsHeaders = new WithCorsHeader();
    HttpHeaders headers = withCorsHeaders.getHeaders();

    return new ResponseEntity<>(containerResponse, headers, HttpStatus.OK);
  }

  @ResponseBody
  @GetMapping
  public ResponseEntity<List<ContainerResponse>> readAllContainerByUser(@RequestParam String uid,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    List<ContainerResponse> containers = endpoint.readListByUid(uid, jwsToken);

    WithCorsHeader withCorsHeaders = new WithCorsHeader();
    HttpHeaders headers = withCorsHeaders.getHeaders();

    return new ResponseEntity<List<ContainerResponse>>(containers, headers, HttpStatus.OK);
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
