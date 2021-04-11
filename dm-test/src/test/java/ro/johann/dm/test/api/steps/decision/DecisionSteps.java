package ro.johann.dm.test.api.steps.decision;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.DecisionService;
import ro.johann.dm.test.api.service.decision.transfer.CreateDecisionInput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionsOutput;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DecisionSteps {

  private final Storage storage;
  private final DecisionService decisionService;

  private CreateDecisionInput createDecisionInput;

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
      .ifPresent(storage::setDecision);
  }

  @Given("I plan to create a decision")
  public void prepareCreateDecisionInput() {
    createDecisionInput = new CreateDecisionInput();
  }

  @Given("I set the name {string} on the create decision input")
  public void setNameOnCreateDecisionInput(String name) {
    createDecisionInput.setName(name);
  }

  @When("I create the decision")
  public void createDecision() {
    createDecision(createDecisionInput);
  }

  @When("I create a decision with name {string}")
  public void createDecision(String name) {
    createDecision(new CreateDecisionInput(name));
  }

  private void createDecision(CreateDecisionInput request) {
    decisionService.createDecision(request)
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

  @Then("the decisions count is {int}")
  public void theDecisionsCountIs(int count) {
    assertEquals(count, storage.getDecisions().size());
  }

  @Then("the decision has {int} criteria")
  public void theDecisionHasCriteria(int count) {
    assertEquals(count, storage.getDecision().getCriteria().size());
  }
}
