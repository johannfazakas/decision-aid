package ro.johann.da.user.service.error;

public class Errors {
  public static ValidationException userAlreadyExists(String email) {
    return new ValidationException(String.format("User %s already exists.", email));
  }

  public static AuthenticationException wrongEmailOrPassword() {
    return new AuthenticationException("Wrong user or email.");
  }

  public static AuthenticationException invalidToken() {
    return new AuthenticationException("Invalid authentication token.");
  }
}
