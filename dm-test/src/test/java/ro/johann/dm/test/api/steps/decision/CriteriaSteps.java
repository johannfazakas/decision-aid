package ro.johann.dm.test.api.steps.decision;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.decision.CriteriaService;
import ro.johann.dm.test.api.service.decision.transfer.AddCriteriaInput;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CriteriaSteps {

    private final Storage storage;
    private final CriteriaService criteriaService;

    private AddCriteriaInput addCriteriaInput;

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
    public void prepareCriteriaInput() {
        addCriteriaInput = new AddCriteriaInput();
    }

    @Given("I set the name {string} on the criteria input")
    public void setCriteriaInputName(String name) {
        addCriteriaInput.setName(name);
    }

    @Given("I set the weight {int} on the criteria input")
    public void setCriteriaInputWeight(int weight) {
        addCriteriaInput.setWeight(weight);
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

    @Then("the criteria weight is {int}")
    public void theCriteriaWeightIs(int weight) {
        assertEquals(weight, storage.getCriteria().getWeight());
    }

}
