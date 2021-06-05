package ro.johann.da.user.service.error;

public class Errors {
  public static RuntimeException userAlreadyExists(String email) {
    return new ValidationException(String.format("User %s already exists.", email));
  }
}
