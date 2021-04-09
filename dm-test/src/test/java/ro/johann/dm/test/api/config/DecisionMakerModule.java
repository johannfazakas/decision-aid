package ro.johann.dm.test.api.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ro.johann.dm.test.api.Storage;

public class DecisionMakerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Storage.class).in(Scopes.SINGLETON);
    }
}
