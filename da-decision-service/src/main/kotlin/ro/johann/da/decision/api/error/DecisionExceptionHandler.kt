package ro.johann.da.decision.api.error

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ro.johann.da.decision.api.transfer.ErrorOutput
import ro.johann.da.decision.service.error.ConflictException
import ro.johann.da.decision.service.error.NotFoundException

@ControllerAdvice
class DecisionExceptionHandler : ResponseEntityExceptionHandler() {
  private companion object {
    val log: Logger = LoggerFactory.getLogger(DecisionExceptionHandler::class.java)
  }

  private object Message {
    const val INTERNAL_ERROR = "Internal server error."
  }

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(value = [NotFoundException::class])
  @ResponseBody
  fun handleNotFound(exception: NotFoundException): ErrorOutput {
    log.warn("not found >> ${exception.message}")
    return ErrorOutput(exception.message)
  }

  @ResponseStatus(CONFLICT)
  @ExceptionHandler(value = [ConflictException::class])
  @ResponseBody
  fun handleConflict(exception: ConflictException): ErrorOutput {
    log.warn("conflict >> ${exception.message}")
    return ErrorOutput(exception.message)
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = [RuntimeException::class])
  @ResponseBody
  fun handleInternalError(exception: RuntimeException): ErrorOutput {
    log.error("internal error >> ${exception.message}")
    return ErrorOutput(Message.INTERNAL_ERROR)
  }
}