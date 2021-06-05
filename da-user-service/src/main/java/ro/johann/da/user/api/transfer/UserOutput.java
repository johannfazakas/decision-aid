package ro.johann.da.user.api.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ro.johann.da.user.domain.User;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserOutput {
  UUID id;
  String email;

  public UserOutput(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
  }
}
