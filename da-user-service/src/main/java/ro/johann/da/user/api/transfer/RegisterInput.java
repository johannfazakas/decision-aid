package ro.johann.da.user.api.transfer;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ro.johann.da.user.api.validation.FieldsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import static lombok.AccessLevel.PRIVATE;

@Data
@ToString(exclude = {"password", "confirmPassword"})
@FieldDefaults(level = PRIVATE)
@FieldsMatch(message = "Passwords don't match", first = "password", second = "confirmPassword")
public class RegisterInput {
  @Email
  @NotEmpty
  String email;
  @NotEmpty
  String password;
  @NotEmpty
  String confirmPassword;
}
