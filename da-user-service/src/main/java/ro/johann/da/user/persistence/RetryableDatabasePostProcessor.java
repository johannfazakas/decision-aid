package ro.johann.da.user.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class RetryableDatabasePostProcessor implements BeanPostProcessor {

  private static final Logger log = LoggerFactory.getLogger(RetryableDatabasePostProcessor.class);

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

    if (bean instanceof DataSource) {
      log.info("post process before initialization with retryable data source");
      return new RetryableDataSource((DataSource) bean);
    }
    return bean;
  }
}
