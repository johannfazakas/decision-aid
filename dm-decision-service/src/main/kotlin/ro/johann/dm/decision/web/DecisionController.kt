package ro.johann.dm.decision.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.transfer.DecisionTO
import ro.johann.dm.decision.transfer.ListResponse

@RestController
@RequestMapping("/decision/v1/decisions")
class DecisionController {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DecisionController::class.java)
  }

  fun list(): ListResponse<DecisionTO> {
    logger.info("list >>")
    return ListResponse(listOf(), 42)
  }
}