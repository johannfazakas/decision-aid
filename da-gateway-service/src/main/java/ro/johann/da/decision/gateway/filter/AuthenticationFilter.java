package ro.johann.da.decision.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public class AuthenticationFilter implements GatewayFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    var authorizationHeader = exchange.getRequest().getHeaders().getOrDefault("Authorization", List.of());
    if (authorizationHeader.isEmpty() || !authorizationHeader.get(0).equals("Let me in!")) {
      var response = exchange.getResponse();
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      return response.writeWith(Mono.empty());
    }
    return chain.filter(exchange);
  }
}
