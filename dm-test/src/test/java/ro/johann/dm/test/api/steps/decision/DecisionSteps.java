package ro.johann.dm.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.DecisionService;
import ro.johann.dm.test.api.service.decision.transfer.CreateDecisionInput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionsOutput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateDecisionInput;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DecisionSteps {

  private final Storage storage;
  private final DecisionService decisionService;

  private CreateDecisionInput.Builder createDecisionInputBuilder;
  private UpdateDecisionInput.Builder updateDecisionInputBuilder;

  @Inject
  public DecisionSteps(Storage storage, DecisionService decisionService) {
    this.storage = storage;
    this.decisionService = decisionService;
  }

  @Given("I peek at the decision with name {string}")
  public void peekDecisionWithName(String name) {
    storage.getDecisions().stream()
      .filter(it -> name.equals(it.getName()))
      .findFirst()
      .ifPresentOrElse(storage::setDecision, () -> decisionNotFound(name));
  }

  @Given("I plan to create a decision")
  public void prepareCreateDecisionInput() {
    createDecisionInputBuilder = CreateDecisionInput.builder();
  }

  @Given("I plan to update the decision")
  public void prepareUpdateDecisionInput() {
    updateDecisionInputBuilder = UpdateDecisionInput.builder();
  }

  @Given("I set the name {string} on the create decision input")
  public void setNameOnCreateDecisionInput(String name) {
    createDecisionInputBuilder.name(name);
  }

  @Given("I set the name {string} on the update decision input")
  public void setNameOnUpdateDecisionInput(String name) {
    updateDecisionInputBuilder.name(name);
  }

  @Given("I set the description {string} on the create decision input")
  public void setDescriptionOnCreateDecisionInput(String description) {
    createDecisionInputBuilder.description(description);
  }

  @Given("I set the description {string} on the update decision input")
  public void setDescriptionOnUpdateDecisionInput(String description) {
    updateDecisionInputBuilder.description(description);
  }

  @When("I create the decision")
  public void createDecision() {
    createDecision(createDecisionInputBuilder.build());
  }

  @When("I create a decision with name {string}")
  public void createDecision(String name) {
    createDecision(CreateDecisionInput.builder().name(name).build());
  }

  private void createDecision(CreateDecisionInput request) {
    decisionService.createDecision(request)
      .ifPresent(storage::setDecision);
  }

  @When("I update the decision")
  public void updateDecision() {
    updateDecision(storage.getDecision().getId(), updateDecisionInputBuilder.build());
  }

  @When("I update a decision by random id")
  public void updateDecisionByRandomId() {
    updateDecision(UUID.randomUUID().toString(), updateDecisionInputBuilder.build());
  }

  private void updateDecision(String decisionId, UpdateDecisionInput input) {
    decisionService.updateDecision(decisionId, input)
      .ifPresent(storage::setDecision);
  }

  @When("I get the decision")
  public void getDecision() {
    getDecision(storage.getDecision().getId());
  }

  @When("I get a decision by random id")
  public void getDecisionByRandomId() {
    getDecision(UUID.randomUUID().toString());
  }

  private void getDecision(String id) {
    decisionService.getDecision(id)
      .ifPresent(storage::setDecision);
  }

  @When("I list the decisions")
  public void listDecisions() {
    decisionService.listDecisions()
      .map(DecisionsOutput::getItems)
      .ifPresent(storage::setDecisions);
  }

  @When("I delete the decision")
  public void deleteDecision() {
    deleteDecision(storage.getDecision().getId());
  }

  @When("I delete a decision by random id")
  public void deleteDecisionByRandomId() {
    deleteDecision(UUID.randomUUID().toString());
  }

  private void deleteDecision(String id) {
    decisionService.deleteDecision(id);
  }

  @Then("the decision name is {string}")
  public void decisionNameIs(String name) {
    assertEquals(name, storage.getDecision().getName());
  }

  @Then("the decision description is {string}")
  public void decisionDescriptionIs(String name) {
    assertEquals(name, storage.getDecision().getDescription());
  }

  @Then("the decisions count is {int}")
  public void theDecisionsCountIs(int count) {
    assertEquals(count, storage.getDecisions().size());
  }

  @Then("the decision has {int} criteria")
  public void theDecisionHasCriteria(int count) {
    assertEquals(count, storage.getDecision().getCriteria().size());
  }

  @Then("the decision has {int} alternative(s)")
  public void theDecisionHasAlternatives(int count) {
    assertEquals(count, storage.getDecision().getAlternatives().size());
  }

  private static void decisionNotFound(String name) {
    throw new RuntimeException(String.format("Decision not found by name %s.", name));
  }
}
