package ro.johann.da.decision.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ro.johann.da.decision.gateway.error.Errors;
import ro.johann.da.decision.gateway.user.UserService;

import java.util.List;
import java.util.Optional;

public class AuthenticationFilter implements GatewayFilter {

  private static final String USER_ID = "X-DA-UserId";

  private final UserService userService;

  public AuthenticationFilter(UserService userService) {
    this.userService = userService;
  }

  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    return extractToken(exchange)
      .map(t -> userService.validateToken(t)
        .flatMap(tokenOutput -> chain.filter(injectUserId(exchange, tokenOutput.getUserId())))
        .onErrorResume(throwable -> unauthorized(exchange, throwable)))
      .orElse(unauthorized(exchange, Errors.missingToken()));
  }

  private ServerWebExchange injectUserId(ServerWebExchange exchange, String userId) {
    return exchange.mutate().request(
      exchange.getRequest().mutate()
        .header(USER_ID, userId)
        .build())
      .build();
  }

  private Mono<Void> unauthorized(ServerWebExchange exchange, Throwable error) {
    var response = exchange.getResponse();
    response.setStatusCode(HttpStatus.UNAUTHORIZED);
    return response.writeWith(Mono.empty());
  }

  private Optional<String> extractToken(ServerWebExchange exchange) {
    return exchange
      .getRequest()
      .getHeaders()
      .getOrDefault("Authorization", List.of())
      .stream()
      .findFirst()
      .map(t -> t.replace("Bearer ", ""));
  }
}
