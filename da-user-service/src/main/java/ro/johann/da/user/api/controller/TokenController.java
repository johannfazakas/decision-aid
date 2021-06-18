package ro.johann.da.user.api.controller;

import org.springframework.web.bind.annotation.*;
import ro.johann.da.user.api.transfer.GenerateTokenInput;
import ro.johann.da.user.api.transfer.TokenOutput;
import ro.johann.da.user.service.DeleteTokenCommand;
import ro.johann.da.user.service.GenerateTokenCommand;
import ro.johann.da.user.service.ValidateTokenCommand;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("user/v1/tokens")
public class TokenController {

  private final GenerateTokenCommand generateTokenCommand;
  private final ValidateTokenCommand validateTokenCommand;
  private final DeleteTokenCommand deleteTokenCommand;

  public TokenController(
    GenerateTokenCommand generateTokenCommand,
    ValidateTokenCommand validateTokenCommand,
    DeleteTokenCommand deleteTokenCommand
  ) {
    this.generateTokenCommand = generateTokenCommand;
    this.validateTokenCommand = validateTokenCommand;
    this.deleteTokenCommand = deleteTokenCommand;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public TokenOutput generateToken(@Valid @RequestBody GenerateTokenInput input) {
    return new TokenOutput(generateTokenCommand.execute(input));
  }

  @GetMapping("/{token}")
  @ResponseStatus(OK)
  public TokenOutput validateToken(@PathVariable("token") UUID token) {
    return new TokenOutput(validateTokenCommand.execute(token));
  }

  @DeleteMapping("/{token}")
  @ResponseStatus(NO_CONTENT)
  public void deleteToken(@PathVariable("token") UUID token) {
    deleteTokenCommand.execute(token);
  }
}
