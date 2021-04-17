package ro.johann.dm.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.PropertyService;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.PropertyOutput;
import ro.johann.dm.test.api.service.decision.transfer.SetPropertyInput;
import ro.johann.dm.test.api.steps.Errors;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertySteps {

  private final Storage storage;
  private final PropertyService propertyService;

  @Inject
  public PropertySteps(Storage storage, PropertyService propertyService) {
    this.storage = storage;
    this.propertyService = propertyService;
  }

  @When("I set the property on the alternative {string} for the criteria {string} to the value {float}")
  public void setPropertyValue(String alternativeName, String criteriaName, Float value) {
    String alternativeId = getAlternativeByName(alternativeName).getId();
    String criteriaId = getCriteriaByName(criteriaName).getId();
    propertyService.addProperty(storage.getDecision().getId(), alternativeId, criteriaId, new SetPropertyInput(value));
  }

  @Then("the property value for the alternative {string} for the criteria {string} is {float}")
  public void propertyValueIs(String alternativeName, String criteriaName, Float value) {
    var property = getPropertyByNames(alternativeName, criteriaName)
      .orElseThrow(() -> Errors.propertyNotFoundByNames(alternativeName, criteriaName));
    assertEquals(value, property.getValue());
  }

  @Then("the property value for the alternative {string} for the criteria {string} is not set")
  public void propertyValueIsNotSet(String alternativeName, String criteriaName) {
    var property = getPropertyByNames(alternativeName, criteriaName);
    assertTrue(property.isEmpty());
  }

  private Optional<PropertyOutput> getPropertyByNames(String alternativeName, String criteriaName) {
    var alternative = getAlternativeByName(alternativeName);
    var criteria = getCriteriaByName(criteriaName);
    return alternative.getProperties().stream()
      .filter(p -> p.getCriteriaId().equals(criteria.getId()))
      .findFirst();
  }

  private AlternativeOutput getAlternativeByName(String name) {
    return storage.getDecision().getAlternatives().stream()
      .filter(a -> name.equals(a.getName()))
      .findFirst()
      .orElseThrow(() -> Errors.alternativeNotFoundByName(name));
  }

  private CriteriaOutput getCriteriaByName(String name) {
    return storage.getDecision().getCriteria().stream()
      .filter(c -> name.equals(c.getName()))
      .findFirst()
      .orElseThrow(() -> Errors.criteriaNotFoundByName(name));
  }
}
