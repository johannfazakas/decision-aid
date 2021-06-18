package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.johann.da.user.persistence.TokenRepository;

import java.util.UUID;

@Component
public class DeleteTokenCommand {

  private static final Logger log = LoggerFactory.getLogger(DeleteTokenCommand.class);

  private final TokenRepository tokenRepository;

  public DeleteTokenCommand(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  public void execute(UUID token) {
    log.info("delete token >> {}", token);
    tokenRepository.deleteById(token);
  }
}

