package com.splitscale.fordastore.api.url.create;

import java.io.IOException;
import java.security.GeneralSecurityException;

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

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlRequest;
import com.splitscale.fordastore.core.url.UrlResponse;
import com.splitscale.shield.endpoints.url.create.CreateUrlEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization", allowedHeaders = "Authorization")
@RequestMapping("/api/urls")
public class CreateUrlController {
  CreateUrlEndpoint endpoint;

  public CreateUrlController(CreateUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PostMapping(path = "/create")
  public ResponseEntity<UrlResponse> createUrl(@RequestBody UrlRequest urlRequest,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    Url url = endpoint.create(urlRequest, jwsToken);

    UrlResponse urlResponse = new UrlResponse(url.getUrlID(), urlRequest.getInnerUrl());

    return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
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