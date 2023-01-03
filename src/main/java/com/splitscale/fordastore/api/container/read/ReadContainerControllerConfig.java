package com.splitscale.fordastore.api.container.read;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.read.ReadContainerInteractor;
import com.splitscale.shield.endpoints.container.read.ReadContainerEndpoint;

@Configuration
public class ReadContainerControllerConfig {

  @Bean
  public ContainerRepositoryInteractor getContainerRepositoryInteractor() {
    return new ContainerRepositoryInteractor();
  }

  @Bean
  public ReadContainerInteractor getCreateContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new ReadContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public ReadContainerEndpoint getContainerEndpoint(ReadContainerInteractor containerInteractor) {
    return new ReadContainerEndpoint(containerInteractor);
  }
}
