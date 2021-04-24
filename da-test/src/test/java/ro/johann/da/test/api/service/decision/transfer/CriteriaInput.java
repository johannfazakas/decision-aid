package ro.johann.da.test.api.service.decision.transfer;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder(builderClassName = "Builder")
@FieldDefaults(level = PRIVATE)
public class CriteriaInput {
  String name;
  Integer weight;
  String unitOfMeasure;
  String type;
}
