package ro.johann.dm.test.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Storage {

  int responseStatusCode;

  DecisionOutput decision;
  List<DecisionOutput> decisions;

  private CriteriaOutput criteria;

  public void cleanUp() {
    this.responseStatusCode = 0;
    this.decision = null;
    this.decisions = List.of();
    this.criteria = null;
  }
}
