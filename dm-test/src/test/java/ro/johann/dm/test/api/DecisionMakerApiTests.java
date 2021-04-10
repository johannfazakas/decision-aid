package ro.johann.dm.test.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty"
        },
        features = {
                "src/test/resources/features/decision/create_decision.feature",
                "src/test/resources/features/decision/get_decision.feature",
                "src/test/resources/features/decision/list_decisions.feature",
                "src/test/resources/features/decision/delete_decision.feature",
        }
)
public class DecisionMakerApiTests {
}
