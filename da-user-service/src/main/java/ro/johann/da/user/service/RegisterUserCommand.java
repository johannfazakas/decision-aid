package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.johann.da.user.api.transfer.RegisterInput;
import ro.johann.da.user.domain.User;
import ro.johann.da.user.persistence.UserRepository;
import ro.johann.da.user.service.error.Errors;

@Component
public class RegisterUserCommand {

  private static final Logger log = LoggerFactory.getLogger(RegisterUserCommand.class);

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public RegisterUserCommand(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public User execute(RegisterInput input) {
    log.info("register user >> {}", input);
    if (userRepository.existsByEmail(input.getEmail())) {
      throw Errors.userAlreadyExists(input.getEmail());
    }
    return userRepository.save(User.builder()
      .email(input.getEmail())
      .encryptedPassword(passwordEncoder.encode(input.getPassword()))
      .build());
  }
}
