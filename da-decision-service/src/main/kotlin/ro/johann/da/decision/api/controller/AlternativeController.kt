package ro.johann.da.decision.api.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.da.decision.api.transfer.AddAlternativeInput
import ro.johann.da.decision.api.transfer.AlternativeOutput
import ro.johann.da.decision.api.transfer.UpdateAlternativeInput
import ro.johann.da.decision.service.AddAlternativeCommand
import ro.johann.da.decision.service.DeleteAlternativeCommand
import ro.johann.da.decision.service.UpdateAlternativeCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions/{decisionId}/alternatives")
class AlternativeController(
  private val addAlternativeCommand: AddAlternativeCommand,
  private val updateAlternativeCommand: UpdateAlternativeCommand,
  private val deleteAlternativeCommand: DeleteAlternativeCommand
) {
  @PostMapping
  @ResponseStatus(CREATED)
  fun addAlternative(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @Valid @RequestBody input: AddAlternativeInput
  ): AlternativeOutput =
    addAlternativeCommand.execute(userId, decisionId, input)
      .let(::AlternativeOutput)

  @PatchMapping("/{alternativeId}")
  @ResponseStatus(OK)
  fun updateAlternative(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("alternativeId") alternativeId: UUID,
    @Valid @RequestBody input: UpdateAlternativeInput
  ): AlternativeOutput =
    updateAlternativeCommand.execute(userId, decisionId, alternativeId, input)
      .let(::AlternativeOutput)

  @DeleteMapping("/{alternativeId}")
  @ResponseStatus(NO_CONTENT)
  fun deleteAlternative(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("alternativeId") alternativeId: UUID,
  ): Unit =
    deleteAlternativeCommand.execute(userId, decisionId, alternativeId)
}