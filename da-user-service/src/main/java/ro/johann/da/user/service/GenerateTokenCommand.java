package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.johann.da.user.api.transfer.GenerateTokenInput;
import ro.johann.da.user.domain.Token;
import ro.johann.da.user.domain.User;
import ro.johann.da.user.persistence.TokenRepository;
import ro.johann.da.user.persistence.UserRepository;
import ro.johann.da.user.service.error.Errors;

import java.time.LocalDateTime;

@Component
public class GenerateTokenCommand {

  private static final Logger log = LoggerFactory.getLogger(GenerateTokenCommand.class);

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;

  public GenerateTokenCommand(
    UserRepository userRepository,
    TokenRepository tokenRepository,
    PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Token execute(GenerateTokenInput input) {
    log.info("generate token >> input = {}", input);

    User user = userRepository.getByEmail(input.getEmail())
      .filter(u -> passwordEncoder.matches(input.getPassword(), u.getEncryptedPassword()))
      .orElseThrow(Errors::wrongEmailOrPassword);
    return tokenRepository.save(Token.builder()
      .user(user)
      .expiresAt(LocalDateTime.now().plusMinutes(30))
      .build());
  }
}
