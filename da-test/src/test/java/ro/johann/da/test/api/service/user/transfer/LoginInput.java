package ro.johann.da.test.api.service.user.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Value
@FieldDefaults(level = PRIVATE)
@JsonInclude(NON_NULL)
@AllArgsConstructor
public class LoginInput {
  String email;
  String password;
}
