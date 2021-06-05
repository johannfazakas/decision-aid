package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.johann.da.user.domain.Token;
import ro.johann.da.user.domain.User;
import ro.johann.da.user.persistence.TokenRepository;
import ro.johann.da.user.service.error.Errors;

import java.util.UUID;

@Component
public class ValidateTokenCommand {

  private static final Logger log = LoggerFactory.getLogger(ValidateTokenCommand.class);

  private final TokenRepository tokenRepository;

  public ValidateTokenCommand(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  public Token execute(UUID token) {
    log.info("validate token >> {}", token);
    // TODO remove cheat
    if (token.equals(UUID.fromString("11112222-3333-4444-5555-666677778888"))) {
      return Token.builder()
        .user(User.builder()
          .id(UUID.fromString("12345678-1234-1234-1234-123456789012"))
          .build())
        .build();
    }
    return tokenRepository
      .findById(token)
      .filter(Token::isAlive)
      .orElseThrow(Errors::invalidToken);
  }
}
