package com.splitscale.fordastore.api.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.fordastore.core.container.delete.DeleteContainerInteractor;
import com.splitscale.fordastore.core.container.edit.EditContainerInteractor;
import com.splitscale.fordastore.core.container.read.ReadContainerInteractor;
import com.splitscale.shield.endpoints.container.create.CreateContainerEndpoint;
import com.splitscale.shield.endpoints.container.delete.DeleteContainerEndpoint;
import com.splitscale.shield.endpoints.container.edit.EditContainerEndpoint;
import com.splitscale.shield.endpoints.container.read.ReadContainerEndpoint;

@Configuration
public class ContainerControllerConfig {
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
  public CreateContainerEndpoint getCreateContainerEndpoint(CreateContainerInteractor containerInteractor) {
    return new CreateContainerEndpoint(containerInteractor);
  }

  @Bean
  public DeleteContainerInteractor getDeleteContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new DeleteContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public DeleteContainerEndpoint getDeleteContainerEndpoint(DeleteContainerInteractor containerInteractor) {
    return new DeleteContainerEndpoint(containerInteractor);
  }

  @Bean
  public EditContainerInteractor getEditContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new EditContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public EditContainerEndpoint getEditContainerEndpoint(EditContainerInteractor containerInteractor) {
    return new EditContainerEndpoint(containerInteractor);
  }

  @Bean
  public ReadContainerInteractor getReadContainerInteractor(
      ContainerRepositoryInteractor containerRepositoryInteractor) {
    return new ReadContainerInteractor(containerRepositoryInteractor);
  }

  @Bean
  public ReadContainerEndpoint getReadContainerEndpoint(ReadContainerInteractor containerInteractor) {
    return new ReadContainerEndpoint(containerInteractor);
  }
}
