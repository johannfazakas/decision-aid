package ro.johann.da.decision.gateway.error;

public class AuthenticationException extends RuntimeException {
  public AuthenticationException(String message) {
    super(message);
  }
}
