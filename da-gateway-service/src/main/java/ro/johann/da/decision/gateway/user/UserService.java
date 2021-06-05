package ro.johann.da.decision.gateway.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ro.johann.da.decision.gateway.error.Errors;
import ro.johann.da.decision.gateway.user.transfer.TokenOutput;

import java.util.Map;

@Component
public class UserService {

  private final WebClient webClient;

  @Value("${userApi.validateTokenUrl}")
  private String validateTokenUrl;

  public UserService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<TokenOutput> validateToken(String token) {
    return webClient
      .get()
      .uri(UriComponentsBuilder.fromUriString(validateTokenUrl)
        .buildAndExpand(Map.of("token", token))
        .toUriString())
      .exchangeToMono(clientResponse -> {
        if (clientResponse.statusCode().is2xxSuccessful()) {
          return clientResponse.bodyToMono(TokenOutput.class);
        } else {
          return Mono.error(Errors.invalidToken());
        }
      });
  }
}
