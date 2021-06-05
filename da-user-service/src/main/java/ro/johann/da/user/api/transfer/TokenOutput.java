package ro.johann.da.user.api.transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ro.johann.da.user.domain.Token;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TokenOutput {
  UUID token;
  UUID userId;
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  LocalDateTime expiresAt;

  public TokenOutput(Token token) {
    this.token = token.getId();
    this.userId = token.getUser().getId();
    this.expiresAt = token.getExpiresAt();
  }
}
