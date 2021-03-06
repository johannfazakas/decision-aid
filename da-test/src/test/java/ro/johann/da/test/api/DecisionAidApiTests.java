package ro.johann.da.test.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {
    "pretty"
  },
  features = {
    // users
    "src/test/resources/features/user/register.feature",
    // decisions
    "src/test/resources/features/decision/create_decision.feature",
    "src/test/resources/features/decision/get_decision.feature",
    "src/test/resources/features/decision/list_decisions.feature",
    "src/test/resources/features/decision/update_decision.feature",
    "src/test/resources/features/decision/delete_decision.feature",
    // criteria
    "src/test/resources/features/decision/add_criteria.feature",
    "src/test/resources/features/decision/update_criteria.feature",
    "src/test/resources/features/decision/delete_criteria.feature",
    // alternatives
    "src/test/resources/features/decision/add_alternative.feature",
    "src/test/resources/features/decision/update_alternative.feature",
    "src/test/resources/features/decision/delete_alternative.feature",
    // properties
    "src/test/resources/features/decision/set_property.feature",
  }
)
public class DecisionAidApiTests {
}
