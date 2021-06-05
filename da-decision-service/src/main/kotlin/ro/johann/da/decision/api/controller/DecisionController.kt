package ro.johann.da.decision.api.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.da.decision.api.transfer.CreateDecisionInput
import ro.johann.da.decision.api.transfer.DecisionOutput
import ro.johann.da.decision.api.transfer.ListOutput
import ro.johann.da.decision.api.transfer.UpdateDecisionInput
import ro.johann.da.decision.service.AidDecisionCommand
import ro.johann.da.decision.service.CreateDecisionCommand
import ro.johann.da.decision.service.DeleteDecisionCommand
import ro.johann.da.decision.service.GetDecisionCommand
import ro.johann.da.decision.service.ListDecisionsCommand
import ro.johann.da.decision.service.ProcessDecisionCommand
import ro.johann.da.decision.service.ResetDecisionCommand
import ro.johann.da.decision.service.UpdateDecisionCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions")
class DecisionController(
  private val listDecisionsCommand: ListDecisionsCommand,
  private val getDecisionCommand: GetDecisionCommand,
  private val createDecisionCommand: CreateDecisionCommand,
  private val updateDecisionCommand: UpdateDecisionCommand,
  private val deleteDecisionCommand: DeleteDecisionCommand,
  private val aidDecisionCommand: AidDecisionCommand,
  private val resetDecisionCommand: ResetDecisionCommand,
  private val processDecisionCommand: ProcessDecisionCommand,
) {
  @GetMapping
  @ResponseStatus(OK)
  fun listDecisions(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @RequestParam("aid") aid: Boolean = true
  ): ListOutput<DecisionOutput> =
    listDecisionsCommand.execute(userId)
      .map { decision ->
        if (aid) {
          val processingResult = processDecisionCommand.execute(decision)
          DecisionOutput(decision, processingResult)
        } else {
          DecisionOutput(decision)
        }
      }
      .let(::ListOutput)

  @GetMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun getDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @RequestParam("aid") aid: Boolean = false
  ): DecisionOutput =
    getDecisionCommand.execute(userId, decisionId)
      .let { decision ->
        if (aid) {
          val processingResult = processDecisionCommand.execute(decision)
          DecisionOutput(decision, processingResult)
        } else {
          DecisionOutput(decision)
        }
      }

  @PostMapping
  @ResponseStatus(CREATED)
  fun createDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @Valid @RequestBody input: CreateDecisionInput
  ): DecisionOutput =
    createDecisionCommand.execute(userId, input)
      .let(::DecisionOutput)

  @PatchMapping("/{decisionId}")
  @ResponseStatus(OK)
  fun updateDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @Valid @RequestBody input: UpdateDecisionInput
  ): DecisionOutput =
    updateDecisionCommand.execute(userId, decisionId, input)
      .let(::DecisionOutput)

  @DeleteMapping("/{decisionId}")
  @ResponseStatus(NO_CONTENT)
  fun deleteDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID
  ): Unit =
    deleteDecisionCommand.execute(userId, decisionId)

  @PutMapping("/{decisionId}/aid")
  @ResponseStatus(OK)
  fun aidDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID
  ): DecisionOutput =
    aidDecisionCommand.execute(userId, decisionId)
      .let(::DecisionOutput)

  @PutMapping("/{decisionId}/reset")
  @ResponseStatus(OK)
  fun resetDecision(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID
  ): DecisionOutput =
    resetDecisionCommand.execute(userId, decisionId)
      .let(::DecisionOutput)
}