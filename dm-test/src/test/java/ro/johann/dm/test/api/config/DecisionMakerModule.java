package ro.johann.dm.test.api.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.steps.decision.CriteriaSteps;
import ro.johann.dm.test.api.steps.decision.DecisionSteps;

import java.net.http.HttpClient;

public class DecisionMakerModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Storage.class).in(Scopes.SINGLETON);

    bind(HttpClient.class).toInstance(HttpClient.newHttpClient());

    bind(DecisionSteps.class).in(Scopes.SINGLETON);
    bind(CriteriaSteps.class).in(Scopes.SINGLETON);
  }
}
