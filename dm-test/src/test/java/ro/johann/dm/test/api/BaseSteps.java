package ro.johann.dm.test.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.inject.Inject;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseSteps {

    private final Storage storage;

    @Inject
    public BaseSteps(Storage storage) {
        this.storage = Objects.requireNonNull(storage);
    }

    @Then("the response is {int}")
    public void theResponseIs(int response) {
        assertEquals(response, storage.getResponseStatusCode());
    }

    @When("check storage is empty")
    public void checkStorageIsEmpty() {
        assertEquals(this.storage.getResponseStatusCode(), 0);
        assertTrue(this.storage.getDecisions().isEmpty());
    }
}
