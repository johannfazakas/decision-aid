package ro.johann.dm.test.api.steps.decision;

import io.cucumber.java8.En;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.AlternativeService;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeInput;

import javax.inject.Inject;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;

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
  }
}
