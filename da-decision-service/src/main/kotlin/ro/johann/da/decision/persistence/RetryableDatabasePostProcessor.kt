package ro.johann.da.decision.persistence

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
class RetryableDatabasePostProcessor : BeanPostProcessor {

  companion object {
    private val log = LoggerFactory.getLogger(RetryableDatabasePostProcessor::class.java)
  }

  override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? = when (bean) {
    is DataSource -> {
      log.info("post process before initialization with retryable data source")
      RetryableDataSource(bean)
    }
    else -> super.postProcessBeforeInitialization(bean, beanName)
  }

  override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? = bean
}