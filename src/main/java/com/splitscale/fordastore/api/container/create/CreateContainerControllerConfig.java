package com.splitscale.fordastore.api.container.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.shield.auth.AuthPublicKeyInteractor;
import com.splitscale.shield.endpoints.ContainerEndpoint;

@Configuration
public class CreateContainerControllerConfig {
  @Bean
  public ContainerRepositoryInteractor getContainerRepositoryInteractor() {
    return new ContainerRepositoryInteractor();
  }

  @Bean
  public CreateContainerInteractor getCreateContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new CreateContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public ContainerEndpoint getContainerEndpoint(CreateContainerInteractor containerInteractor,
      AuthPublicKeyInteractor authPublicKeyInteractor) {
    return new ContainerEndpoint(containerInteractor, authPublicKeyInteractor);
  }
}
