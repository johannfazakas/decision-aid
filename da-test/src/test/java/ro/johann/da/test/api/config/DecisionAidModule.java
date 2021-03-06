package ro.johann.da.test.api.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import lombok.SneakyThrows;
import ro.johann.da.test.api.common.Storage;

import java.net.http.HttpClient;
import java.util.Properties;

public class DecisionAidModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Storage.class).in(Scopes.SINGLETON);

    bind(HttpClient.class).toInstance(HttpClient.newHttpClient());
  }

  @SneakyThrows
  @Provides
  public Properties getProperties() {
    var configInputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
    Properties props = new Properties();
    props.load(configInputStream);
    return props;
  }
}
