package com.splitscale.fordastore.api.cors;

import org.springframework.http.HttpHeaders;

public class WithCorsHeader {

  private final HttpHeaders headers;

  public WithCorsHeader() {
    headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    headers.add("Access-Control-Allow-Headers", "origin, content-type, accept");
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

}
