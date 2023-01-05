package com.splitscale.fordastore.api.url.edit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlResponse;
import com.splitscale.shield.endpoints.url.edit.EditUrlEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "Authorization", allowedHeaders = "Authorization")
@RequestMapping("/api/urls")
public class EditUrlController {
  EditUrlEndpoint endpoint;

  public EditUrlController(EditUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PutMapping(path = "/{urlId}")
  public ResponseEntity<UrlResponse> createContainer(@RequestBody Url url,
      @RequestHeader(value = "authorization") String jwsToken)
      throws IOException, GeneralSecurityException {

    endpoint.edit(url, jwsToken);

    UrlResponse urlResponse = new UrlResponse(url.getUrlID(), url.getInnerUrl());

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
