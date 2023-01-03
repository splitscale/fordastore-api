package com.splitscale.fordastore.api.url;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.URLRepositoryInteractor;
import com.splitscale.fordastore.core.url.create.CreateUrlInteractor;
import com.splitscale.fordastore.core.url.delete.DeleteUrlInteractor;
import com.splitscale.fordastore.core.url.edit.EditUrlInteractor;
import com.splitscale.shield.endpoints.url.create.CreateUrlEndpoint;
import com.splitscale.shield.endpoints.url.delete.DeleteUrlEndpoint;
import com.splitscale.shield.endpoints.url.edit.EditUrlEndpoint;

@Configuration
public class UrlControllerConfig {

  @Bean
  public URLRepositoryInteractor getUrlRepositoryInteractor() {
    return new URLRepositoryInteractor();
  }

  @Bean
  public CreateUrlInteractor getUrlCreateUrlInteractor(
      URLRepositoryInteractor urlRepositoryInteractor) {
    return new CreateUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public CreateUrlEndpoint getCreateUrlEndpoint(CreateUrlInteractor urlInteractor) {
    return new CreateUrlEndpoint(urlInteractor);
  }

  @Bean
  public DeleteUrlInteractor getUrlDeleteUrlInteractor(URLRepositoryInteractor urlRepositoryInteractor) {
    return new DeleteUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public DeleteUrlEndpoint getDeleteUrlEndpoint(DeleteUrlInteractor urlInteractor) {
    return new DeleteUrlEndpoint(urlInteractor);
  }

  @Bean
  public EditUrlInteractor getEditUrlInteractor(
      URLRepositoryInteractor urlRepositoryInteractor) {
    return new EditUrlInteractor(urlRepositoryInteractor);
  }

  @Bean
  public EditUrlEndpoint getEditUrlEndpoint(EditUrlInteractor urlInteractor) {
    return new EditUrlEndpoint(urlInteractor);
  }
}
