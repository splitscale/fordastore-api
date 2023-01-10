package com.splitscale.fordastore.api.container.edit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.shield.endpoints.container.edit.EditContainerEndpoint;

@RestController
@CrossOrigin
@RequestMapping("/api/containers")
public class EditContainerController {
  EditContainerEndpoint endpoint;

  public EditContainerController(EditContainerEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @ResponseBody
  @PutMapping("/{id}")
  public ResponseEntity<Container> editContainer(@PathVariable Long cid, @RequestBody ContainerRequest containerRequest,
      @RequestHeader(value = "Authorization") String jwsToken) throws IOException, GeneralSecurityException {

    Container container = new Container();
    container.setContainerID(cid);
    container.setName(containerRequest.getName());
    container.setUid(containerRequest.getUid());

    endpoint.edit(container, jwsToken);

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
