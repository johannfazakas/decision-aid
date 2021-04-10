package ro.johann.dm.decision.api

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
import ro.johann.dm.decision.transfer.ListTO
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions")
class DecisionController(
  private val listDecisionsCommand: ListDecisionsCommand,
  private val getDecisionCommand: GetDecisionCommand,
  private val createDecisionCommand: CreateDecisionCommand,
  private val deleteDecisionCommand: DeleteDecisionCommand
) {
  @GetMapping
  @ResponseStatus(OK)
  fun list(): ListTO<DecisionTO> =
    listDecisionsCommand.execute()
      .map(::DecisionTO)
      .let(::ListTO)

  @GetMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun get(@PathVariable("decisionId") id: UUID): DecisionTO =
    getDecisionCommand.execute(id)
      .let(::DecisionTO)

  @PostMapping
  @ResponseStatus(CREATED)
  fun create(@Valid @RequestBody request: CreateDecisionRequest): DecisionTO =
    createDecisionCommand.execute(request)
      .let(::DecisionTO)

  @DeleteMapping("/{decisionId}")
  @ResponseStatus(NO_CONTENT)
  fun delete(@PathVariable("decisionId") id: UUID) =
    deleteDecisionCommand.execute(id)
}