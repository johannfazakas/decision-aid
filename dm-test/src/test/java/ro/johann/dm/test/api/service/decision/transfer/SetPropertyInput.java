package ro.johann.dm.test.api.service.decision.transfer;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class SetPropertyInput {
  Float value;
}
