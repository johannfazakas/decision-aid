package ro.johann.dm.test.api.service.decision.transfer;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class DecisionsOutput {
  List<DecisionOutput> items;
}
