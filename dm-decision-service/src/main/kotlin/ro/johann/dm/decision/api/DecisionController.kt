package ro.johann.dm.decision.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.service.CreateDecisionCommand
import ro.johann.dm.decision.service.DeleteDecisionCommand
import ro.johann.dm.decision.service.GetDecisionCommand
import ro.johann.dm.decision.service.ListDecisionsCommand
import ro.johann.dm.decision.transfer.CreateDecisionRequest
import ro.johann.dm.decision.transfer.DecisionTO
import ro.johann.dm.decision.transfer.DecisionsTO
import java.util.UUID

@RestController
@RequestMapping("/decision/v1/decisions")
class DecisionController(
  private val listDecisionsCommand: ListDecisionsCommand,
  private val getDecisionCommand: GetDecisionCommand,
  private val createDecisionCommand: CreateDecisionCommand,
  private val deleteDecisionCommand: DeleteDecisionCommand
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DecisionController::class.java)
  }

  @GetMapping
  @ResponseStatus(OK)
  fun list(): DecisionsTO {
    logger.info("list decisions")
    return listDecisionsCommand.execute()
      .map(::DecisionTO)
      .let(::DecisionsTO)
  }

  @GetMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun get(@PathVariable("decisionId") id: UUID): DecisionTO {
    logger.info("get decision >> id = $id")
    return getDecisionCommand.execute(id)
      .let(::DecisionTO)
  }

  @PostMapping
  @ResponseStatus(CREATED)
  fun create(@RequestBody request: CreateDecisionRequest): DecisionTO {
    logger.info("create decision >> request = $request")
    return createDecisionCommand.execute(request)
      .let(::DecisionTO)
  }

  @DeleteMapping("/{decisionId}")
  @ResponseStatus(NO_CONTENT)
  fun delete(@PathVariable("decisionId") id: UUID) {
    logger.info("delete decision >> id = $id")
    deleteDecisionCommand.execute(id)
  }
}