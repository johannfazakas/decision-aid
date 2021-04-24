package ro.johann.da.test.api.steps.decision;

import io.cucumber.java8.En;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.decision.transfer.CriteriaInput;
import ro.johann.da.test.api.service.decision.CriteriaService;
import ro.johann.da.test.api.service.decision.transfer.CriteriaOutput;

import javax.inject.Inject;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;

public class CriteriaSteps implements En {

  @Inject
  public CriteriaSteps(Storage storage, CriteriaService criteriaService) {

    Given("I peek at the criteria with name {string}", (String name) ->
      storage.setCriteria(storage.getCriteriaByName(name)));

    Given("I plan to add/update a/the criteria", () ->
      storage.setCriteriaInputBuilder(CriteriaInput.builder()));

    Given("I set the criteria name to {string}", (String name) ->
      storage.getCriteriaInputBuilder().name(name));

    Given("I set the criteria weight to {int}", (Integer weight) ->
      storage.getCriteriaInputBuilder().weight(weight));

    Given("I set the criteria unit of measure to {string}", (String unitOfMeasure) ->
      storage.getCriteriaInputBuilder().unitOfMeasure(unitOfMeasure));

    Given("I set the criteria type to {string}", (String type) ->
      storage.getCriteriaInputBuilder().type(type));

    Given("I use a nonexistent criteria", () ->
      storage.setCriteria(CriteriaOutput.builder().id(UUID.randomUUID().toString()).build()));

    When("I add the criteria", () ->
      criteriaService.addCriteria(storage.getDecision().getId(), storage.getCriteriaInputBuilder().build())
        .ifPresent(criteria -> {
          // TODO refactor, encapsulate
          storage.setCriteria(criteria);
          ofNullable(storage.getDecision())
            .ifPresent(d -> d.addCriteria(criteria));
        }));

    When("I update the criteria", () ->
      criteriaService.updateCriteria(
        storage.getDecision().getId(),
        storage.getCriteria().getId(),
        storage.getCriteriaInputBuilder().build())
        .ifPresent(storage::setCriteria));

    When("I delete the criteria with name {string}", (String name) ->
      criteriaService.deleteCriteria(storage.getDecision().getId(), storage.getCriteriaByName(name).getId()));

    Then("the criteria weight is {int}", (Integer weight) ->
      assertEquals((int) weight, storage.getCriteria().getWeight()));

    Then("the criteria name is {string}", (String name) ->
      assertEquals(name, storage.getCriteria().getName()));

    Then("the criteria unit of measure is {string}", (String unitOfMeasure) ->
      assertEquals(unitOfMeasure, storage.getCriteria().getUnitOfMeasure()));

    Then("the criteria type is {string}", (String type) ->
      assertEquals(type, storage.getCriteria().getType()));
  }
}
