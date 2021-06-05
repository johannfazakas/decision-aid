package ro.johann.da.decision.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ro.johann.da.decision.gateway.user.UserService;

@Configuration
public class FilterConfig {

  @Bean
  public AuthenticationFilter authenticationFilter(UserService userService) {
    return new AuthenticationFilter(userService);
  }

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }
}
