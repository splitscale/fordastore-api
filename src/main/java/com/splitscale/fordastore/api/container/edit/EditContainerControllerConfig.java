package com.splitscale.fordastore.api.container.edit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.edit.EditContainerInteractor;
import com.splitscale.shield.endpoints.container.edit.EditContainerEndpoint;

@Configuration
public class EditContainerControllerConfig {

  @Bean
  public ContainerRepositoryInteractor getContainerRepositoryInteractor() {
    return new ContainerRepositoryInteractor();
  }

  @Bean
  public EditContainerInteractor getCreateContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new EditContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public EditContainerEndpoint getContainerEndpoint(EditContainerInteractor containerInteractor) {
    return new EditContainerEndpoint(containerInteractor);
  }
}
