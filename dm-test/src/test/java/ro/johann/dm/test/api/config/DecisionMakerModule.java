package ro.johann.dm.test.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ro.johann.dm.test.api.steps.BaseSteps;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.steps.decision.DecisionSteps;

import java.net.http.HttpClient;

public class DecisionMakerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Storage.class).in(Scopes.SINGLETON);
        bind(DecisionSteps.class).in(Scopes.SINGLETON);
        bind(BaseSteps.class).in(Scopes.SINGLETON);
        bind(HttpService.class).in(Scopes.SINGLETON);
        bind(HttpClient.class).toInstance(HttpClient.newHttpClient());
        bind(ObjectMapper.class).toInstance(new ObjectMapper());
    }
}
