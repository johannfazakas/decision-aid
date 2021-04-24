package ro.johann.dm.test.api.steps;

import io.cucumber.java8.En;
import ro.johann.dm.test.api.common.Storage;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class BaseSteps implements En {

  @Inject
  public BaseSteps(Storage storage) {
    Then("the response is {int}", (Integer response) ->
      assertEquals((int) response, storage.getResponseStatusCode()));
  }
}
