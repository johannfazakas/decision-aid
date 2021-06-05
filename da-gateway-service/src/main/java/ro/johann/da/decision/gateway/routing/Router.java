package ro.johann.da.decision.gateway.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.johann.da.decision.gateway.filter.AuthenticationFilter;

@Configuration
public class Router {

  @Bean
  public AuthenticationFilter authenticationFilter() {
    return new AuthenticationFilter();
  }

  @Bean
  public RouteLocator route(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
    return builder.routes()
      .route(r -> r
        .path("/decision/v1/**")
        .filters(f -> f.filter(new AuthenticationFilter()))
        .uri("http://localhost:7032/"))
      .route(r -> r
        .path("/user/v1/**")
        .uri("http://localhost:7087"))
      .build();
  }
}
