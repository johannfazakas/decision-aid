package ro.johann.da.test.api.common;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ro.johann.da.test.api.service.decision.transfer.*;
import ro.johann.da.test.api.steps.Errors;

import java.util.List;

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

  private DecisionInput.Builder decisionInputBuilder;
  private AlternativeInput.Builder alternativeInputBuilder;
  private CriteriaInput.Builder criteriaInputBuilder;

  public void cleanUp() {
    this.responseStatusCode = 0;
    this.decision = null;
    this.decisions = List.of();
    this.criteria = null;
    this.alternative = null;
    this.property = null;
  }

  public DecisionOutput getDecisionByName(String name) {
    return decisions.stream()
      .filter(d -> name.equals(d.getName()))
      .findFirst()
      .orElseThrow(() -> Errors.decisionNotFoundByName(name));
  }

  public AlternativeOutput getAlternativeByName(String name) {
    return decision.getAlternatives().stream()
      .filter(a -> name.equals(a.getName()))
      .findFirst()
      .orElseThrow(() -> Errors.alternativeNotFoundByName(name));
  }

  public CriteriaOutput getCriteriaByName(String name) {
    return decision.getCriteria().stream()
      .filter(c -> name.equals(c.getName()))
      .findFirst()
      .orElseThrow(() -> Errors.criteriaNotFoundByName(name));
  }

  public PropertyOutput getPropertyByAlternativeAndCriteriaNames(String alternativeName, String criteriaName) {
    return decision.getProperties().stream()
      .filter(p -> p.getAlternativeId().equals(getAlternativeByName(alternativeName).getId()))
      .filter(p -> p.getCriteriaId().equals(getCriteriaByName(criteriaName).getId()))
      .findFirst()
      .orElseThrow(() -> Errors.propertyNotFoundByNames(alternativeName, criteriaName));
  }

}
