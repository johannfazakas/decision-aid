package ro.johann.da.user.api.controller;

import org.springframework.web.bind.annotation.*;
import ro.johann.da.user.api.transfer.GenerateTokenInput;
import ro.johann.da.user.api.transfer.TokenOutput;
import ro.johann.da.user.service.GenerateTokenCommand;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("user/v1/tokens")
public class TokenController {

  private final GenerateTokenCommand generateTokenCommand;

  public TokenController(GenerateTokenCommand generateTokenCommand) {
    this.generateTokenCommand = generateTokenCommand;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public TokenOutput generateToken(@Valid @RequestBody GenerateTokenInput input) {
    return new TokenOutput(generateTokenCommand.execute(input));
  }
}
