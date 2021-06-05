package ro.johann.da.decision.api.controller

import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.da.decision.api.transfer.PropertyOutput
import ro.johann.da.decision.api.transfer.SetPropertyInput
import ro.johann.da.decision.service.SetPropertyCommand
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
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @RequestBody @Valid input: SetPropertyInput
  ): PropertyOutput =
    setPropertyCommand.execute(userId, decisionId, input)
      .let(::PropertyOutput)
}