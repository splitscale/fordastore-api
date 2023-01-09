package com.splitscale.fordastore.api.url.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

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

import com.splitscale.fordastore.core.url.UrlResponse;
import com.splitscale.shield.endpoints.url.read.ReadUrlEndpoint;

@RestController
@CrossOrigin
@RequestMapping("/api/urls")
public class ReadUrlController {
  private ReadUrlEndpoint endpoint;

  public ReadUrlController(ReadUrlEndpoint endpoint) {
    this.endpoint = endpoint;
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<UrlResponse>> readUrlByContainerId(@RequestParam(value = "cid") Long cid,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    List<UrlResponse> urls = endpoint.getALLByContainerID(cid, jwsToken);

    return new ResponseEntity<List<UrlResponse>>(urls, HttpStatus.OK);
  }

  @GetMapping(path = "/{urlId}")
  @ResponseBody
  public ResponseEntity<UrlResponse> readUrlByUrlId(@PathVariable Long urlId,
      @RequestHeader(value = "authorization") String jwsToken) throws IOException, GeneralSecurityException {

    UrlResponse url = endpoint.getByUrlID(urlId, jwsToken);

    return new ResponseEntity<UrlResponse>(url, HttpStatus.OK);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    System.out.println("[urls] IOException: " + e.getMessage());

    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    System.out.println("[urls] GeneralSecurityException: " + e.getMessage());

    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
