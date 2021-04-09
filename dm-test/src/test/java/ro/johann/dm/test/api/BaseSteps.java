package ro.johann.dm.test.api;

import io.cucumber.java.en.Then;

public class BaseSteps {
    @Then("the response is {int}")
    public void theResponseIs(int response) {

    }
}
