package ro.johann.da.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ro.johann.da.user.persistence.UserRepository;

import java.util.UUID;

@Component
public class DeleteUserCommand {

  private static final Logger log = LoggerFactory.getLogger(DeleteUserCommand.class);

  private final UserRepository userRepository;

  public DeleteUserCommand(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void execute(UUID userId) {
    log.info("delete user >> {}", userId);
    try {
      userRepository.deleteById(userId);
    } catch (EmptyResultDataAccessException exception) {
      log.info("user with id {} did not exist.", userId, exception);
    }
  }
}
