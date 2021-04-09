package ro.johann.dm.test.api.decision;

import io.cucumber.java.en.When;
import ro.johann.dm.test.api.Storage;
import ro.johann.dm.test.api.decision.transfer.CreateDecision;
import ro.johann.dm.test.api.decision.transfer.Decision;

public class DecisionSteps {

    private DecisionService decisionService = new DecisionService();

    private Decision decision = null;

    @When("I create a decision with name {string}")
    public void createDecision(String name) throws Exception {
        var response = decisionService.createDecision(new CreateDecision(name, null));
        this.decision = response.getObject();
        Storage.setResponseStatus(response.getStatusCode());
    }

    @When("I store the decision as {string}")
    public void storeDecisionAs(String alias) {
        // TODO johann
    }

    @When("I delete the decision {string}")
    public void deleteDecision(String alias) {
        // TODO johann
    }
}
