package ro.johann.dm.test.api.steps.decision;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.CriteriaService;
import ro.johann.dm.test.api.service.decision.transfer.AddCriteriaInput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateCriteriaInput;
import ro.johann.dm.test.api.steps.Errors;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CriteriaSteps {

  private final Storage storage;
  private final CriteriaService criteriaService;

  private AddCriteriaInput.Builder addCriteriaInputBuilder;
  private UpdateCriteriaInput.Builder updateCriteriaInputBuilder;

  @Inject
  public CriteriaSteps(Storage storage, CriteriaService criteriaService) {
    this.storage = storage;
    this.criteriaService = criteriaService;
  }

  @Given("I peek at the criteria with name {string}")
  public void peekAtCriteria(String name) {
    storage.getDecision().getCriteria().stream()
      .filter(it -> name.equals(it.getName()))
      .findFirst()
      .ifPresentOrElse(storage::setCriteria, () -> {
        throw Errors.criteriaNotFoundByName(name);
      });
  }

  @Given("I plan to add a criteria")
  public void prepareAddCriteriaInput() {
    addCriteriaInputBuilder = AddCriteriaInput.builder();
  }

  @Given("I set the name to {string} on the add criteria input")
  public void setAddCriteriaInputName(String name) {
    addCriteriaInputBuilder.name(name);
  }

  @Given("I set the weight to {int} on the add criteria input")
  public void setAddCriteriaInputWeight(int weight) {
    addCriteriaInputBuilder.weight(weight);
  }

  @Given("I set the unit of measure to {string} on the add criteria input")
  public void setAddCriteriaInputUnitOfMeasure(String unitOfMeasure) {
    addCriteriaInputBuilder.unitOfMeasure(unitOfMeasure);
  }

  @Given("I set the type to {string} on the add criteria input")
  public void setAddCriteriaInputType(String type) {
    addCriteriaInputBuilder.type(type);
  }

  @Given("I plan to update the criteria")
  public void prepareUpdateCriteriaInput() {
    updateCriteriaInputBuilder = UpdateCriteriaInput.builder();
  }

  @Given("I set the weight to {int} on the update criteria input")
  public void setUpdateCriteriaInputWeight(int weight) {
    updateCriteriaInputBuilder.weight(weight);
  }

  @Given("I set the name to {string} on the update criteria input")
  public void setUpdateCriteriaInputName(String name) {
    updateCriteriaInputBuilder.name(name);
  }


  @Given("I set the unit of measure to {string} on the update criteria input")
  public void setUpdateCriteriaInputUnitOfMeasure(String unitOfMeasure) {
    updateCriteriaInputBuilder.unitOfMeasure(unitOfMeasure);
  }

  @Given("I set the type to {string} on the update criteria input")
  public void setUpdateCriteriaInputType(String type) {
    updateCriteriaInputBuilder.type(type);
  }

  @When("I add the criteria")
  public void addCriteria() {
    addCriteria(storage.getDecision().getId(), addCriteriaInputBuilder.build());
  }

  @When("I add the criteria on a random decision")
  public void addCriteriaOnRandomDecision() {
    addCriteria(UUID.randomUUID().toString(), addCriteriaInputBuilder.build());
  }

  private void addCriteria(String decisionId, AddCriteriaInput input) {
    criteriaService.addCriteria(decisionId, input)
      .ifPresent(storage::setCriteria);
  }

  @When("I update the criteria by random criteria")
  public void updateCriteriaOnRandomCriteria() {
    updateCriteria(storage.getDecision().getId(), UUID.randomUUID().toString(), updateCriteriaInputBuilder.build());
  }

  @When("I update the criteria by random decision")
  public void updateCriteriaOnRandomDecision() {
    updateCriteria(UUID.randomUUID().toString(), storage.getDecision().getId(), updateCriteriaInputBuilder.build());
  }

  @When("I update the criteria")
  public void iUpdateTheCriteria() {
    updateCriteria(storage.getDecision().getId(), storage.getCriteria().getId(), updateCriteriaInputBuilder.build());
  }

  private void updateCriteria(String decisionId, String criteriaId, UpdateCriteriaInput input) {
    criteriaService.updateCriteria(decisionId, criteriaId, input)
      .ifPresent(storage::setCriteria);
  }

  @When("I delete the criteria with name {string}")
  public void deleteCriteriaWithName(String name) {
    storage.getDecision().getCriteria().stream()
      .filter(c -> name.equals(c.getName()))
      .findFirst()
      .ifPresentOrElse(c -> criteriaService.deleteCriteria(storage.getDecision().getId(), c.getId()), () -> {
        throw Errors.criteriaNotFoundByName(name);
      });
  }

  @Then("the criteria weight is {int}")
  public void theCriteriaWeightIs(int weight) {
    assertEquals(weight, storage.getCriteria().getWeight());
  }

  @Then("the criteria name is {string}")
  public void theCriteriaNameIs(String name) {
    assertEquals(name, storage.getCriteria().getName());
  }

  @Then("the criteria unit of measure is {string}")
  public void theCriteriaUnitOfMeasureIs(String unitOfMeasure) {
    assertEquals(unitOfMeasure, storage.getCriteria().getUnitOfMeasure());
  }

  @Then("the criteria type is {string}")
  public void theCriteriaTypeIs(String type) {
    assertEquals(type, storage.getCriteria().getType());
  }
}
