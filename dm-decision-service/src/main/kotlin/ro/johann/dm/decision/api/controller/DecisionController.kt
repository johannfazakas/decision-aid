package ro.johann.dm.decision.api.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.api.transfer.CreateDecisionInput
import ro.johann.dm.decision.api.transfer.DecisionOutput
import ro.johann.dm.decision.api.transfer.ListOutput
import ro.johann.dm.decision.api.transfer.UpdateDecisionInput
import ro.johann.dm.decision.service.CreateDecisionCommand
import ro.johann.dm.decision.service.DeleteDecisionCommand
import ro.johann.dm.decision.service.GetDecisionCommand
import ro.johann.dm.decision.service.ListDecisionsCommand
import ro.johann.dm.decision.service.UpdateDecisionCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions")
class DecisionController(
  private val listDecisionsCommand: ListDecisionsCommand,
  private val getDecisionCommand: GetDecisionCommand,
  private val createDecisionCommand: CreateDecisionCommand,
  private val updateDecisionCommand: UpdateDecisionCommand,
  private val deleteDecisionCommand: DeleteDecisionCommand
) {
  @GetMapping
  @ResponseStatus(OK)
  fun listDecisions(): ListOutput<DecisionOutput> =
    listDecisionsCommand.execute()
      .map(::DecisionOutput)
      .let(::ListOutput)

  @GetMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun getDecision(
    @PathVariable("decisionId") id: UUID
  ): DecisionOutput =
    getDecisionCommand.execute(id)
      .let(::DecisionOutput)

  @PostMapping
  @ResponseStatus(CREATED)
  fun createDecision(
    @Valid @RequestBody input: CreateDecisionInput
  ): DecisionOutput =
    createDecisionCommand.execute(input)
      .let(::DecisionOutput)

  @PatchMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun updateDecision(
    @PathVariable("decisionId") id: UUID,
    @Valid @RequestBody input: UpdateDecisionInput
  ): DecisionOutput =
    updateDecisionCommand.execute(id, input)
      .let(::DecisionOutput)

  @DeleteMapping("/{decisionId}")
  @ResponseStatus(NO_CONTENT)
  fun deleteDecision(
    @PathVariable("decisionId") id: UUID
  ): Unit =
    deleteDecisionCommand.execute(id)
}