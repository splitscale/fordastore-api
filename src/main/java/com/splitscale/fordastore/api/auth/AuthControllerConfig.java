package com.splitscale.fordastore.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.user.login.LoginInteractor;
import com.splitscale.fordastore.core.user.register.RegisterInteractor;
import com.splitscale.shield.endpoints.LoginEndpoint;
import com.splitscale.shield.endpoints.RegisterEndpoint;

@Configuration
public class AuthControllerConfig {

  @Bean
  public UserRepositoryInteractor getUserRepositoryInteractor() {
    return new UserRepositoryInteractor();
  }

  @Bean
  public RegisterInteractor getRegisterInteractor(UserRepositoryInteractor userRepositoryInteractor) {
    return new RegisterInteractor(userRepositoryInteractor);
  }

  @Bean
  public LoginInteractor getLoginInteractor(UserRepositoryInteractor userRepositoryInteractor) {
    return new LoginInteractor(userRepositoryInteractor);
  }

  @Bean
  public RegisterEndpoint registerEndpoint(RegisterInteractor registerInteractor) {
    return new RegisterEndpoint(registerInteractor);
  }

  @Bean
  public LoginEndpoint loginEndpoint(LoginInteractor loginInteractor) {
    return new LoginEndpoint(loginInteractor);
  }
}
