package com.splitscale.fordastore.api.url.delete;

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

import com.splitscale.shield.endpoints.url.delete.DeleteUrlEndpoint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/container")
public class DeleteUrlController {
  DeleteUrlEndpoint endpoint;

  public DeleteUrlController(DeleteUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PostMapping(path = "/delete")
  public ResponseEntity<String> createContainer(@RequestBody Long containerId,@RequestHeader(value = "uid") String uid)
    {
    try {
      endpoint.delete(containerId, uid);

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
