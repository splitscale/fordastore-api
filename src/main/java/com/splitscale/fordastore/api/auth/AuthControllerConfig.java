package com.splitscale.fordastore.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.user.add.AddUserInteractor;
import com.splitscale.fordastore.core.user.get.GetUserInteractor;
import com.splitscale.shield.endpoints.auth.LoginEndpoint;
import com.splitscale.shield.endpoints.auth.RegisterEndpoint;

@Configuration
public class AuthControllerConfig {

  @Bean
  public UserRepositoryInteractor getUserRepositoryInteractor() {
    return new UserRepositoryInteractor();
  }

  @Bean
  public AddUserInteractor getRegisterInteractor(UserRepositoryInteractor userRepositoryInteractor) {
    return new AddUserInteractor(userRepositoryInteractor);
  }

  @Bean
  public GetUserInteractor getLoginInteractor(UserRepositoryInteractor userRepositoryInteractor) {
    return new GetUserInteractor(userRepositoryInteractor);
  }

  @Bean
  public RegisterEndpoint registerEndpoint(AddUserInteractor registerInteractor) {
    return new RegisterEndpoint(registerInteractor);
  }

  @Bean
  public LoginEndpoint loginEndpoint(GetUserInteractor loginInteractor) {
    return new LoginEndpoint(loginInteractor);
  }
}
