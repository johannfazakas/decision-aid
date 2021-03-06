package ro.johann.da.test.api.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.ScenarioScope;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.decision.DecisionService;
import ro.johann.da.test.api.service.user.UserService;

public class DecisionAidObjectFactory implements ObjectFactory {

  private final Injector injector;

  public DecisionAidObjectFactory() {
    this.injector = Guice.createInjector(
      Stage.DEVELOPMENT,
      CucumberModules.createScenarioModule(),
      new DecisionAidModule());
  }

  @Override
  public void start() {
    this.injector.getInstance(ScenarioScope.class).enterScope();
  }

  @Override
  public void stop() {
    // TODO find another solution for cleanup
    this.injector.getInstance(ScenarioScope.class).exitScope();
    this.injector.getInstance(DecisionService.class).cleanUp();
    this.injector.getInstance(UserService.class).cleanUp();
    this.injector.getInstance(Storage.class).cleanUp();
  }

  @Override
  public boolean addClass(Class<?> glueClass) {
    return true;
  }

  @Override
  public <T> T getInstance(Class<T> glueClass) {
    return this.injector.getInstance(glueClass);
  }
}
