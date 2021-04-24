package ro.johann.dm.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java8.En;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.DecisionService;
import ro.johann.dm.test.api.service.decision.transfer.DecisionInput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionsOutput;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DecisionSteps implements En {

  @Inject
  public DecisionSteps(Storage storage, DecisionService decisionService) {

    Given("I peek at the decision with name {string}", (String name) ->
      storage.setDecision(storage.getDecisionByName(name)));

    Given("I plan to create/update a/the decision", () ->
      storage.setDecisionInputBuilder(DecisionInput.builder()));

    Given("I set the decision name to {string}", (String name) ->
      storage.getDecisionInputBuilder().name(name));

    Given("I set the decision description to {string}", (String description) ->
      storage.getDecisionInputBuilder().description(description));

    Given("I use a nonexistent decision", () ->
      storage.setDecision(DecisionOutput.builder().id(UUID.randomUUID().toString()).build()));

    When("I create the decision", () ->
      decisionService.createDecision(storage.getDecisionInputBuilder().build())
        .ifPresent(storage::setDecision));

    When("I update the decision", () ->
      decisionService.updateDecision(storage.getDecision().getId(), storage.getDecisionInputBuilder().build())
        .ifPresent(storage::setDecision));

    When("I get the decision", () ->
      decisionService.getDecision(storage.getDecision().getId())
        .ifPresent(storage::setDecision));

    When("I list the decisions", () ->
      decisionService.listDecisions()
        .map(DecisionsOutput::getItems)
        .ifPresent(storage::setDecisions));

    When("I delete the decision", () ->
      decisionService.deleteDecision(storage.getDecision().getId()));

    When("I request aid for the decision", () ->
      decisionService.aidDecision(storage.getDecision().getId())
        .ifPresent(storage::setDecision));

    Then("the decision name is {string}", (String name) ->
      assertEquals(name, storage.getDecision().getName()));

    Then("the decision description is {string}", (String name) ->
      assertEquals(name, storage.getDecision().getDescription()));

    Then("the decisions count is {int}", (Integer count) ->
      assertEquals((int) count, storage.getDecisions().size()));

    Then("the decision has {int} criteria", (Integer count) ->
      assertEquals((int) count, storage.getDecision().getCriteria().size()));

    Then("the decision has {int} alternative(s)", (Integer count) ->
      assertEquals((int) count, storage.getDecision().getAlternatives().size()));

    Then("the decision has {int} properties", (Integer count) ->
      assertEquals((int) count, storage.getDecision().getProperties().size()));

    Then("the decision status is {string}", (String status) ->
      assertEquals(status, storage.getDecision().getStatus()));
  }
}
