package ro.johann.da.test.api.common;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ro.johann.da.test.api.service.decision.transfer.*;
import ro.johann.da.test.api.service.user.transfer.RegisterInput;
import ro.johann.da.test.api.service.user.transfer.TokenOutput;
import ro.johann.da.test.api.service.user.transfer.UserOutput;
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

  private UserOutput user;
  // TODO remove hardcoded token
  private TokenOutput token = TokenOutput.builder().token("11112222-3333-4444-5555-666677778888").build();

  private DecisionInput.Builder decisionInputBuilder;
  private AlternativeInput.Builder alternativeInputBuilder;
  private CriteriaInput.Builder criteriaInputBuilder;
  private RegisterInput.Builder registerInputBuilder;

  public void cleanUp() {
    this.responseStatusCode = 0;
    this.decision = null;
    this.decisions = List.of();
    this.criteria = null;
    this.alternative = null;
    this.property = null;
    this.user = null;
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
