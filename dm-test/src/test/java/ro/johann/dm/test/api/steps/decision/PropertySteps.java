package ro.johann.dm.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java8.En;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.PropertyService;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.SetPropertyInput;
import ro.johann.dm.test.api.steps.Errors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertySteps implements En {

  @Inject
  public PropertySteps(Storage storage, PropertyService propertyService) {

    When("I set the property on the alternative {string} for the criteria {string} to the value {float}",
      (String alternativeName, String criteriaName, Float value) -> {
        SetPropertyInput input = SetPropertyInput.builder()
          .alternativeId(storage.getAlternativeByName(alternativeName)
            .map(AlternativeOutput::getId)
            .orElseThrow(() -> Errors.alternativeNotFoundByName(alternativeName)))
          .criteriaId(storage.getCriteriaByName(criteriaName)
            .map(CriteriaOutput::getId)
            .orElseThrow(() -> Errors.criteriaNotFoundByName(criteriaName)))
          .value(value)
          .build();
        propertyService.addProperty(storage.getDecision().getId(), input).ifPresent(storage::setProperty);
      });

    Then("the property value for the alternative {string} for the criteria {string} is {float}",
      (String alternativeName, String criteriaName, Float value) -> {
        var property = storage.getPropertyByAlternativeAndCriteriaNames(alternativeName, criteriaName)
          .orElseThrow(() -> Errors.propertyNotFoundByNames(alternativeName, criteriaName));
        assertEquals(value, property.getValue());
      });

    Then("the property value for the alternative {string} for the criteria {string} is not set",
      (String alternativeName, String criteriaName) -> {
        assertTrue(storage.getPropertyByAlternativeAndCriteriaNames(alternativeName, criteriaName).isEmpty());
      });

    Then("the property value is {float}", (Float value) -> {
      assertEquals(value, storage.getProperty().getValue());
    });
  }
}
