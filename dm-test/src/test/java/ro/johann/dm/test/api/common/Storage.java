package ro.johann.dm.test.api.common;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;
import ro.johann.dm.test.api.service.decision.transfer.PropertyOutput;
import ro.johann.dm.test.api.steps.Errors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Storage {

  int responseStatusCode;

  DecisionOutput decision;
  List<DecisionOutput> decisions;

  private CriteriaOutput criteria;

  private AlternativeOutput alternative;

  private PropertyOutput property;

  public void cleanUp() {
    this.responseStatusCode = 0;
    this.decision = null;
    this.decisions = List.of();
    this.criteria = null;
    this.alternative = null;
    this.property = null;
  }

  public Optional<PropertyOutput> getPropertyByAlternativeAndCriteriaNames(String alternativeName, String criteriaName) {
    var alternativeId = getAlternativeByName(alternativeName).map(AlternativeOutput::getId);
    var criteriaId = getCriteriaByName(criteriaName).map(CriteriaOutput::getId);
    return decision.getProperties().stream()
      .filter(p -> alternativeId.isPresent() && p.getAlternativeId().equals(alternativeId.get()))
      .filter(p -> criteriaId.isPresent() && p.getCriteriaId().equals(criteriaId.get()))
      .findFirst();
  }

  public Optional<AlternativeOutput> getAlternativeByName(String name) {
    return decision.getAlternatives().stream()
      .filter(a -> name.equals(a.getName()))
      .findFirst();
  }

  public Optional<CriteriaOutput> getCriteriaByName(String name) {
    return decision.getCriteria().stream()
      .filter(c -> name.equals(c.getName()))
      .findFirst();
  }
}
