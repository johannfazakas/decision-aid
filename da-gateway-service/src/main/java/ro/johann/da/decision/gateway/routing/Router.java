package ro.johann.da.decision.gateway.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class Router {

  @Bean
  public RouteLocator route(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(r -> r
        .path("/decision/v1/**")
        .filters(f -> f.addRequestHeader("Accept", "application/json"))
        .uri("http://localhost:7032/"))
      .build();
  }
}
