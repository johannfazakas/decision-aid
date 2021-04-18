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
    SetPropertyInput input = SetPropertyInput.builder()
      .alternativeId(getAlternativeByName(alternativeName).getId())
      .criteriaId(getCriteriaByName(criteriaName).getId())
      .value(value)
      .build();
    propertyService.addProperty(storage.getDecision().getId(), input)
      .ifPresent(storage::setProperty);
  }

  @Then("the property value for the alternative {string} for the criteria {string} is {float}")
  public void propertyValueForAlternativeAndCriteriaIs(String alternativeName, String criteriaName, Float value) {
    var property = getPropertyByNames(alternativeName, criteriaName)
      .orElseThrow(() -> Errors.propertyNotFoundByNames(alternativeName, criteriaName));
    assertEquals(value, property.getValue());
  }

  @Then("the property value for the alternative {string} for the criteria {string} is not set")
  public void propertyValueIsNotSet(String alternativeName, String criteriaName) {
    var property = getPropertyByNames(alternativeName, criteriaName);
    assertTrue(property.isEmpty());
  }

  @Then("the property value is {float}")
  public void propertyValueIs(Float value) {
    assertEquals(value, storage.getProperty().getValue());
  }

  private Optional<PropertyOutput> getPropertyByNames(String alternativeName, String criteriaName) {
    var alternative = getAlternativeByName(alternativeName);
    var criteria = getCriteriaByName(criteriaName);
    return storage.getDecision().getProperties().stream()
      .filter(p -> p.getAlternativeId().equals(alternative.getId()))
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
