package ro.johann.da.test.api.steps.user;

import ro.johann.da.test.api.service.user.transfer.RegisterInput;

import java.util.UUID;

public class UserGenerator {

  public static final String DEFAULT_PASSWORD = "P@ssw0rd";

  public static RegisterInput generate() {
    return RegisterInput.builder()
      .email(String.format("user_%s@mail.com", UUID.randomUUID()))
      .password(DEFAULT_PASSWORD)
      .confirmPassword(DEFAULT_PASSWORD)
      .build();
  }
}
