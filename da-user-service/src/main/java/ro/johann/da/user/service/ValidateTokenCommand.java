package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.johann.da.user.domain.Token;
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
    return tokenRepository
      .findById(token)
      .filter(Token::isAlive)
      .orElseThrow(Errors::invalidToken);
  }
}
