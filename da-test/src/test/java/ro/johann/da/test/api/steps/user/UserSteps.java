package ro.johann.da.test.api.steps.user;

import com.google.inject.Inject;
import io.cucumber.java8.En;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.user.UserService;
import ro.johann.da.test.api.service.user.transfer.LoginInput;
import ro.johann.da.test.api.service.user.transfer.RegisterInput;

import static org.junit.Assert.assertEquals;

public class UserSteps implements En {

  @Inject
  public UserSteps(Storage storage, UserService userService) {
    Given("I use a valid user", () -> {
      userService.registerUser(UserGenerator.generate())
        .ifPresent(storage::setUser);
      userService.loginUser(new LoginInput(storage.getUser().getEmail(), UserGenerator.DEFAULT_PASSWORD))
        .ifPresent(storage::setToken);
    });

    Given("I plan to register a user", () ->
      storage.setRegisterInputBuilder(RegisterInput.builder()));

    And("I set the user email to {string}", (String email) ->
      storage.getRegisterInputBuilder().email(email));

    And("I set the user password to {string}", (String password) ->
      storage.getRegisterInputBuilder().password(password));

    And("I set the user confirm password to {string}", (String confirmPassword) ->
      storage.getRegisterInputBuilder().confirmPassword(confirmPassword));

    When("I register the user", () ->
      userService.registerUser(storage.getRegisterInputBuilder().build())
        .ifPresent(storage::setUser));

    And("the user email is {string}", (String email) ->
      assertEquals(email, storage.getUser().getEmail()));
  }
}
