package ro.johann.da.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java8.En;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.decision.PropertyService;
import ro.johann.da.test.api.service.decision.transfer.SetPropertyInput;

import static org.junit.Assert.*;

public class PropertySteps implements En {

  @Inject
  public PropertySteps(Storage storage, PropertyService propertyService) {

    Given("I peek at the property on the alternative {string} for the criteria {string}",
      (String alternativeName, String criteriaName) ->
        storage.setProperty(storage.getPropertyByAlternativeAndCriteriaNames(alternativeName, criteriaName)));

    When("I set the property value to {float} on the alternative {string} for the criteria {string}",
      (Float value, String alternativeName, String criteriaName) -> {
        SetPropertyInput input = SetPropertyInput.builder()
          .alternativeId(storage.getAlternativeByName(alternativeName).getId())
          .criteriaId(storage.getCriteriaByName(criteriaName).getId())
          .value(value)
          .build();
        propertyService.addProperty(storage.getDecision().getId(), input).ifPresent(storage::setProperty);
      });

    Then("the property value for the alternative {string} for the criteria {string} is {float}",
      (String alternativeName, String criteriaName, Float value) -> {
        var property = storage.getPropertyByAlternativeAndCriteriaNames(alternativeName, criteriaName);
        assertEquals(value, property.getValue());
      });

    Then("the property utility is not set", () -> assertNull(storage.getProperty().getUtility()));

    Then("the property utility is {float}", (Float utility) ->
      assertEquals(utility, storage.getProperty().getUtility()));

    Then("the property rank is not set", () -> assertNull(storage.getProperty().getUtility()));

    Then("the property rank is {int}", (Integer rank) ->
      assertEquals(rank, storage.getProperty().getRank()));

    Then("the property value is {float}", (Float value) ->
      assertEquals(value, storage.getProperty().getValue()));
  }
}
