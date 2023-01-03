package com.splitscale.fordastore.api.container.delete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.delete.DeleteContainerInteractor;
import com.splitscale.shield.endpoints.container.delete.DeleteContainerEndpoint;

@Configuration
public class DeleteContainerControllerConfig {
  
  @Bean
  public ContainerRepositoryInteractor getContainerRepositoryInteractor() {
    return new ContainerRepositoryInteractor();
  }

  @Bean
  public DeleteContainerInteractor getCreateContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new DeleteContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public DeleteContainerEndpoint getContainerEndpoint(DeleteContainerInteractor containerInteractor) {
    return new DeleteContainerEndpoint(containerInteractor);
  }
}
