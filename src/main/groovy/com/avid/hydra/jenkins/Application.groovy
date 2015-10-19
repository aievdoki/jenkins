package com.avid.hydra.jenkins

import com.avid.hydra.jenkins.api.management.User
import com.avid.hydra.jenkins.impl.management.UserManagerImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
public class Application {
  @Bean
  public User loggedUser() {
    return new UserManagerImpl().getUser("gromit")
  }

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String... args) {
    SpringApplication.run(Application.class);
  }
}