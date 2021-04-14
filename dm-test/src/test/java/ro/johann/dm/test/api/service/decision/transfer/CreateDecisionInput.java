package ro.johann.dm.test.api.service.decision.transfer;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder(builderClassName = "Builder")
@FieldDefaults(level = PRIVATE)
public class CreateDecisionInput {
  String name;
  String description;
}
