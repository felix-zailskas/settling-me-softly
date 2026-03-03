package io.github.felixzailskas.settlingmesoftly.controller;

import io.github.felixzailskas.settlingmesoftly.api.HealthApi;
import io.github.felixzailskas.settlingmesoftly.model.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi {

  @Override
  public ResponseEntity<HealthResponse> healthCheck() {
    return ResponseEntity.ok(new HealthResponse().status("OK"));
  }
}
