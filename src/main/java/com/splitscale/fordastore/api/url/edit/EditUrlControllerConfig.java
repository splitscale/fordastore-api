package com.splitscale.fordastore.api.url.edit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.URLRepositoryInteractor;
import com.splitscale.fordastore.core.url.edit.EditUrlInteractor;
import com.splitscale.shield.endpoints.url.edit.EditUrlEndpoint;

@Configuration
public class EditUrlControllerConfig {
  
  @Bean
  public URLRepositoryInteractor getUrlRepositoryInteractor() {
    return new URLRepositoryInteractor();
  }

  @Bean
  public EditUrlInteractor getUrlContainerInteractor(
      URLRepositoryInteractor urlRepositoryInteractor) {
    return new EditUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public EditUrlEndpoint getContainerEndpoint(EditUrlInteractor urlInteractor) {
    return new EditUrlEndpoint(urlInteractor);
  }
}
