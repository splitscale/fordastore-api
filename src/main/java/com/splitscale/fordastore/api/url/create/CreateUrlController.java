package com.splitscale.fordastore.api.url.create;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.url.UrlRequest;
import com.splitscale.shield.endpoints.url.create.CreateUrlEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/container")
public class CreateUrlController {
  CreateUrlEndpoint endpoint;

  public CreateUrlController(CreateUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PostMapping(path = "/create")
  public ResponseEntity<String> createContainer(@RequestBody UrlRequest urlRequest,@RequestHeader(value = "uid") String uid)
    {
    try {
      endpoint.create(urlRequest, uid);

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (GeneralSecurityException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }
}
