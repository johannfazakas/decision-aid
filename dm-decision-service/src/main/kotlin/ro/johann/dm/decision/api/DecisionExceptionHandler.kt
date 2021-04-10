package ro.johann.dm.decision.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ro.johann.dm.decision.error.NotFoundException
import ro.johann.dm.decision.transfer.ErrorTO

@ControllerAdvice
class DecisionExceptionHandler : ResponseEntityExceptionHandler() {
  private companion object {
    val log: Logger = LoggerFactory.getLogger(DecisionExceptionHandler::class.java)
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = [NotFoundException::class])
  fun handleNotFound(exception: NotFoundException): ErrorTO {
    log.warn(exception.message)
    return ErrorTO(exception.message)
  }
}