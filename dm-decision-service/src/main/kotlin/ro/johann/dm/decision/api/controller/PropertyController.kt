package ro.johann.dm.decision.api.controller

import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.api.transfer.SetPropertyInput
import ro.johann.dm.decision.service.SetPropertyCommand
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions/{decisionId}/alternatives/{alternativeId}/criteria/{criteriaId}/property")
class PropertyController(
  private val setPropertyCommand: SetPropertyCommand
) {

  @PutMapping
  @ResponseStatus(NO_CONTENT)
  fun setProperty(
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("alternativeId") alternativeId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID,
    @RequestBody @Valid input: SetPropertyInput
  ): Unit =
    setPropertyCommand.execute(decisionId, alternativeId, criteriaId, input)
}