package ro.johann.dm.test.api.service.decision.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddAlternativeInput {
  String name;
}
