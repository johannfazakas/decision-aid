package ro.johann.dm.test.api;

import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseSteps {

    @Then("the response is {int}")
    public void theResponseIs(int response) {
        assertEquals(response, Storage.getResponseStatus());
    }
}
