package ro.johann.dm.test.api.decision;

import com.google.inject.Inject;
import io.cucumber.java.en.When;
import ro.johann.dm.test.api.Storage;
import ro.johann.dm.test.api.decision.transfer.CreateDecision;
import ro.johann.dm.test.api.decision.transfer.Decision;

public class DecisionSteps {

    private final DecisionService decisionService;
    private final Storage storage;

    private Decision decision = null;

    @Inject
    public DecisionSteps(Storage storage, DecisionService decisionService) {
        this.storage = storage;
        this.decisionService = decisionService;
    }

    @When("I create a decision with name {string}")
    public void createDecision(String name) {
        var response = decisionService.createDecision(new CreateDecision(name));
        this.decision = response.getObject();
        this.storage.setResponseStatusCode(response.getStatusCode());
    }

    @When("I store the decision as {string}")
    public void storeDecisionAs(String alias) {
        this.storage.addDecision(alias, this.decision);
    }

    @When("I delete the decision {string}")
    public void deleteDecision(String alias) {
        // TODO johann
    }
}
