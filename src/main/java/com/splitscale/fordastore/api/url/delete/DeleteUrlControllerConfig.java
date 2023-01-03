package com.splitscale.fordastore.api.url.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.URLRepositoryInteractor;
import com.splitscale.fordastore.core.url.delete.DeleteUrlInteractor;
import com.splitscale.shield.endpoints.url.delete.DeleteUrlEndpoint;

@Configuration
public class DeleteUrlControllerConfig {
  
  @Bean
  public URLRepositoryInteractor getUrlRepositoryInteractor() {
    return new URLRepositoryInteractor();
  }

  @Bean
  public DeleteUrlInteractor getUrlContainerInteractor(
      URLRepositoryInteractor urlRepositoryInteractor) {
    return new DeleteUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public DeleteUrlEndpoint getContainerEndpoint(DeleteUrlInteractor urlInteractor) {
    return new DeleteUrlEndpoint(urlInteractor);
  }
}
