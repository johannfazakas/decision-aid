package ro.johann.da.user.service.error;

public class ValidationException extends RuntimeException {
  public ValidationException(String message) {
    super(message);
  }
}
