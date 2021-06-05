package ro.johann.da.decision.gateway.error;

public class Errors {
  public static AuthenticationException invalidToken() {
    return new AuthenticationException("Invalid token");
  }

  public static AuthenticationException missingToken() {
    return new AuthenticationException("Missing token");
  }
}
