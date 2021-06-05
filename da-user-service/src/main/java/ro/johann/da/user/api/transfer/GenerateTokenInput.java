package ro.johann.da.user.api.transfer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(exclude = "password")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenerateTokenInput {
  @NotEmpty
  String email;
  @NotEmpty
  String password;
}
