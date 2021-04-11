package ro.johann.dm.test.api.steps.decision;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.CriteriaService;
import ro.johann.dm.test.api.service.decision.transfer.AddCriteriaInput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateCriteriaInput;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CriteriaSteps {

    private final Storage storage;
    private final CriteriaService criteriaService;

    private AddCriteriaInput addCriteriaInput;
    private UpdateCriteriaInput updateCriteriaInput;

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
                .ifPresent(storage::setCriteria);
    }

    @Given("I plan to add a criteria")
    public void prepareAddCriteriaInput() {
        addCriteriaInput = new AddCriteriaInput();
    }

    @Given("I plan to update the criteria")
    public void prepareUpdateCriteriaInput() {
        updateCriteriaInput = new UpdateCriteriaInput();
    }

    @Given("I set the name {string} on the add criteria input")
    public void setAddCriteriaInputName(String name) {
        addCriteriaInput.setName(name);
    }

    @Given("I set the weight {int} on the add criteria input")
    public void setAddCriteriaInputWeight(int weight) {
        addCriteriaInput.setWeight(weight);
    }

    @Given("I set the weight {int} on the update criteria input")
    public void setUpdateCriteriaInputWeight(int weight) {
        updateCriteriaInput.setWeight(weight);
    }

    @When("I add the criteria")
    public void addCriteria() {
        addCriteria(storage.getDecision().getId(), addCriteriaInput);
    }

    @When("I add the criteria on a random decision")
    public void addCriteriaOnRandomDecision() {
        addCriteria(UUID.randomUUID().toString(), addCriteriaInput);
    }

    @When("I add a criteria with name {string} and weight {int}")
    public void addCriteria(String name, int weight) {
        addCriteria(storage.getDecision().getId(), new AddCriteriaInput(name, weight));
    }

    private void addCriteria(String decisionId, AddCriteriaInput input) {
        criteriaService.addCriteria(decisionId, input)
                .ifPresent(storage::setCriteria);
    }

    @When("I update the criteria by random criteria")
    public void updateCriteriaOnRandomCriteria() {
        updateCriteria(storage.getDecision().getId(), UUID.randomUUID().toString(), updateCriteriaInput);
    }

    @When("I update the criteria by random decision")
    public void updateCriteriaOnRandomDecision() {
        updateCriteria(UUID.randomUUID().toString(), storage.getDecision().getId(), updateCriteriaInput);
    }

    @When("I update the criteria weight to {int}")
    public void updateTheCriteriaWeightTo(int weight) {
        updateCriteria(storage.getDecision().getId(), storage.getCriteria().getId(), new UpdateCriteriaInput(weight));
    }

    private void updateCriteria(String decisionId, String criteriaId, UpdateCriteriaInput input) {
        criteriaService.updateCriteria(decisionId, criteriaId, input)
                .ifPresent(storage::setCriteria);
    }

    @Then("the criteria weight is {int}")
    public void theCriteriaWeightIs(int weight) {
        assertEquals(weight, storage.getCriteria().getWeight());
    }

}
