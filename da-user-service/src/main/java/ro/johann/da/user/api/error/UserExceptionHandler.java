package ro.johann.da.user.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.johann.da.user.api.transfer.ErrorOutput;
import ro.johann.da.user.service.error.AuthenticationException;
import ro.johann.da.user.service.error.ValidationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = ValidationException.class)
  @ResponseBody
  public ErrorOutput handleValidationException(ValidationException exception) {
    log.warn("validation error >> {}", exception.getMessage());
    return new ErrorOutput(exception.getMessage());
  }

  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(value = AuthenticationException.class)
  @ResponseBody
  public ErrorOutput handleAuthenticationException(AuthenticationException exception) {
    log.warn("authentication error >> {}", exception.getMessage());
    return new ErrorOutput(exception.getMessage());
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ErrorOutput handleException(Exception exception) {
    log.warn("internal error >> {}", exception.getMessage());
    return new ErrorOutput(exception.getMessage());
  }
}
