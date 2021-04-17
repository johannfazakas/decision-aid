package ro.johann.dm.test.api.steps.decision;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.AlternativeService;
import ro.johann.dm.test.api.service.decision.transfer.AddAlternativeInput;

import javax.inject.Inject;
import java.util.UUID;

public class AlternativeSteps {

  private final Storage storage;
  private final AlternativeService alternativeService;

  private AddAlternativeInput.Builder addAlternativeInputBuilder;

  @Inject
  public AlternativeSteps(Storage storage, AlternativeService alternativeService) {
    this.storage = storage;
    this.alternativeService = alternativeService;
  }

  @Given("I plan to add an alternative")
  public void prepareAddAlternativeInput() {
    addAlternativeInputBuilder = AddAlternativeInput.builder();
  }

  @Given("I set the name {string} on the add alternative input")
  public void setNameOnAddAlternativeInput(String name) {
    addAlternativeInputBuilder.name(name);
  }

  @Given("I peek at the alternative with name {string}")
  public void peekAtAlternative(String name) {
    storage.getDecision().getAlternatives().stream()
      .filter(it -> name.equals(it.getName()))
      .findFirst()
      .ifPresent(storage::setAlternative);
  }

  @When("I add the alternative")
  public void addAlternative() {
    addAlternative(storage.getDecision().getId(), addAlternativeInputBuilder.build());
  }

  @When("I add the alternative on a random decision")
  public void addAlternativeOnRandomDecision() {
    addAlternative(UUID.randomUUID().toString(), addAlternativeInputBuilder.build());
  }

  private void addAlternative(String decisionId, AddAlternativeInput input) {
    alternativeService.addAlternative(decisionId, input)
      .ifPresent(storage::setAlternative);
  }
}
