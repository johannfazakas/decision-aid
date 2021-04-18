package ro.johann.dm.decision.api.controller

import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.api.transfer.PropertyOutput
import ro.johann.dm.decision.api.transfer.SetPropertyInput
import ro.johann.dm.decision.service.SetPropertyCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions/{decisionId}/properties")
class PropertyController(
  private val setPropertyCommand: SetPropertyCommand
) {

  @PutMapping
  @ResponseStatus(OK)
  fun setProperty(
    @PathVariable("decisionId") decisionId: UUID,
    @RequestBody @Valid input: SetPropertyInput
  ): PropertyOutput =
    setPropertyCommand.execute(decisionId, input)
      .let(::PropertyOutput)
}