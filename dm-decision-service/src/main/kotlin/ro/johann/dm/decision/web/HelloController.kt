package ro.johann.dm.decision.web

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
  private companion object {
    val logger = LoggerFactory.getLogger(HelloController::class.java)
  }

  @GetMapping("/hello")
  @ResponseStatus(HttpStatus.OK)
  fun hello() = "hello"
}