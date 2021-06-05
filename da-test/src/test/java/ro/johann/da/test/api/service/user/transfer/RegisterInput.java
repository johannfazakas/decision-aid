package ro.johann.da.test.api.service.user.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Value
@Builder(builderClassName = "Builder")
@FieldDefaults(level = PRIVATE)
@JsonInclude(NON_NULL)
public class RegisterInput {
  String email;
  String password;
  String confirmPassword;
}
