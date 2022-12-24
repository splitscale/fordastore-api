package com.splitscale.fordastore.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.ditabys.repositories.AuthRepositoryInteractor;
import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.user.register.RegisterInteractor;
import com.splitscale.shield.endpoints.RegisterEndpoint;
import com.splitscale.shield.jwt.JwtInteractor;

@Configuration
public class AuthControllerConfig {

  private RegisterInteractor getRegisterInteractor() {
    return new RegisterInteractor(new UserRepositoryInteractor());
  }

  private JwtInteractor getJKwtInteractor() {
    return new JwtInteractor(new AuthRepositoryInteractor());
  }

  @Bean
  public RegisterEndpoint registerEndpoint() {
    return new RegisterEndpoint(getRegisterInteractor(), getJKwtInteractor());
  }
}
