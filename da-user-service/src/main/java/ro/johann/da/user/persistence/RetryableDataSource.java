package ro.johann.da.user.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class RetryableDataSource extends AbstractDataSource {

  private static final Logger log = LoggerFactory.getLogger(RetryableDataSource.class);

  private final DataSource dataSource;

  public RetryableDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  @Retryable(maxAttempts = 5, backoff = @Backoff(multiplier = 1.3, maxDelay = 20_000))
  public Connection getConnection() throws SQLException {
    log.info("get connection");
    return dataSource.getConnection();
  }

  @Override
  @Retryable(maxAttempts = 5, backoff = @Backoff(multiplier = 1.3, maxDelay = 20_000))
  public Connection getConnection(String username, String password) throws SQLException {
    log.info("get connection >> username = " + username);
    return dataSource.getConnection(username, password);
  }
}
