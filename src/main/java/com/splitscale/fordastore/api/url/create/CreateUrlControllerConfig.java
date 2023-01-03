package com.splitscale.fordastore.api.url.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.URLRepositoryInteractor;
import com.splitscale.fordastore.core.url.create.CreateUrlInteractor;
import com.splitscale.shield.endpoints.url.create.CreateUrlEndpoint;

@Configuration
public class CreateUrlControllerConfig {

  @Bean
  public URLRepositoryInteractor getUrlRepositoryInteractor() {
    return new URLRepositoryInteractor();
  }

  @Bean
  public CreateUrlInteractor getUrlContainerInteractor(
      URLRepositoryInteractor urlRepositoryInteractor) {
    return new CreateUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public CreateUrlEndpoint getContainerEndpoint(CreateUrlInteractor urlInteractor) {
    return new CreateUrlEndpoint(urlInteractor);
  }
}
