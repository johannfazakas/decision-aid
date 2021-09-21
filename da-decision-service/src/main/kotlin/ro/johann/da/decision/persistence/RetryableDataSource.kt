package ro.johann.da.decision.persistence

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.datasource.AbstractDataSource
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import java.sql.Connection
import javax.sql.DataSource

open class RetryableDataSource(private val dataSource: DataSource) : AbstractDataSource() {

  companion object {
    private val log: Logger = LoggerFactory.getLogger(RetryableDataSource::class.java)
  }

  @Retryable(maxAttempts = 5, backoff = Backoff(multiplier = 1.3, maxDelay = 20_000))
  override fun getConnection(): Connection {
    log.info("get connection")
    return dataSource.connection
  }

  @Retryable(maxAttempts = 5, backoff = Backoff(multiplier = 1.3, maxDelay = 20_000))
  override fun getConnection(username: String?, password: String?): Connection {
    log.info("get connection >> username = $username")
    return dataSource.getConnection(username, password)
  }
}