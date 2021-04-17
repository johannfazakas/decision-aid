package ro.johann.dm.decision.api.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.api.transfer.AddAlternativeInput
import ro.johann.dm.decision.api.transfer.AlternativeOutput
import ro.johann.dm.decision.service.AddAlternativeCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions/{decisionId}/alternatives")
class AlternativeController(
  private val addAlternativeCommand: AddAlternativeCommand
) {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun addAlternative(
    @PathVariable("decisionId") decisionId: UUID,
    @Valid @RequestBody input: AddAlternativeInput
  ): AlternativeOutput =
    addAlternativeCommand.execute(decisionId, input)
      .let(::AlternativeOutput)
}