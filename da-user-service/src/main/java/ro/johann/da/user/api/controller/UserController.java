package ro.johann.da.user.api.controller;

import org.springframework.web.bind.annotation.*;
import ro.johann.da.user.api.transfer.RegisterInput;
import ro.johann.da.user.api.transfer.UserOutput;
import ro.johann.da.user.service.DeleteUserCommand;
import ro.johann.da.user.service.GenerateTokenCommand;
import ro.johann.da.user.service.RegisterUserCommand;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/user/v1/users")
public class UserController {

  private final RegisterUserCommand registerUserCommand;
  private final DeleteUserCommand deleteUserCommand;

  public UserController(
    RegisterUserCommand registerUserCommand,
    DeleteUserCommand deleteUserCommand,
    GenerateTokenCommand generateTokenCommand
  ) {
    this.registerUserCommand = registerUserCommand;
    this.deleteUserCommand = deleteUserCommand;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public UserOutput registerUser(@Valid @RequestBody RegisterInput input) {
    return new UserOutput(registerUserCommand.execute(input));
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(NO_CONTENT)
  public void deleteUser(@PathVariable("userId") UUID userId) {
    deleteUserCommand.execute(userId);
  }
}
