package ro.johann.da.test.api.steps.decision;

import io.cucumber.java8.En;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.decision.AlternativeService;
import ro.johann.da.test.api.service.decision.transfer.AlternativeInput;

import javax.inject.Inject;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AlternativeSteps implements En {

  @Inject
  public AlternativeSteps(Storage storage, AlternativeService alternativeService) {

    Given("I plan to add/update an/the alternative", () ->
      storage.setAlternativeInputBuilder(AlternativeInput.builder()));

    Given("I set the alternative name to {string}", (String name) ->
      storage.getAlternativeInputBuilder().name(name));

    Given("I peek at the alternative with name {string}",
      (String name) -> storage.setAlternative(storage.getAlternativeByName(name)));

    When("I add the alternative", () ->
      alternativeService.addAlternative(storage.getDecision().getId(), storage.getAlternativeInputBuilder().build())
        .ifPresent(alternative -> {
          // TODO refactor, encapsulate?
          storage.setAlternative(alternative);
          ofNullable(storage.getDecision()).ifPresent(d -> d.addAlternative(alternative));
        }));

    When("I update the alternative", () ->
      alternativeService.updateAlternative(
        storage.getDecision().getId(),
        storage.getAlternative().getId(),
        storage.getAlternativeInputBuilder().build())
        .ifPresent(storage::setAlternative));

    When("I delete the alternative", () ->
      alternativeService.deleteAlternative(storage.getDecision().getId(), storage.getAlternative().getId()));

    Then("the alternative name is {string}", (String name) ->
      assertEquals(name, storage.getAlternative().getName()));

    Then("the alternative utility is not set", () ->
      assertNull(storage.getAlternative().getUtility()));

    Then("the alternative utility is {float}", (Float utility) ->
      assertEquals(utility, storage.getAlternative().getUtility()));

    Then("the alternative rank is not set", () ->
      assertNull(storage.getAlternative().getRank()));

    Then("the alternative rank is {int}", (Integer rank) ->
      assertEquals(rank, storage.getAlternative().getRank()));
  }
}
