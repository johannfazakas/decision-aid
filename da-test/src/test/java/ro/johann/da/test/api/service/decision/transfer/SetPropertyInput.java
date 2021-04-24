package ro.johann.da.test.api.service.decision.transfer;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder(builderClassName = "Builder")
@FieldDefaults(level = PRIVATE)
public class SetPropertyInput {
  String alternativeId;
  String criteriaId;
  Float value;
}
