package ro.johann.da.user.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ro.johann.da.user.api.transfer.RegisterInput;
import ro.johann.da.user.api.transfer.UserOutput;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/decision/v1/users")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @PostMapping
  @ResponseStatus(CREATED)
  public UserOutput register(@Valid @RequestBody RegisterInput input) {
    return UserOutput.builder()
      .id(UUID.randomUUID())
      .email(input.getEmail())
      .build();
  }
}
