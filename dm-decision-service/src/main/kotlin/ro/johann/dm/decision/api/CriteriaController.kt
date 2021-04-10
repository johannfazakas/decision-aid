package ro.johann.dm.decision.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.dm.decision.service.AddCriteriaCommand
import ro.johann.dm.decision.service.DeleteCriteriaCommand
import ro.johann.dm.decision.service.UpdateCriteriaCommand
import ro.johann.dm.decision.transfer.AddCriteriaInput
import ro.johann.dm.decision.transfer.CriteriaOutput
import ro.johann.dm.decision.transfer.UpdateCriteriaInput
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/decision/v1/decisions/{decisionId}/criteria")
class CriteriaController(
  private val addCriteriaCommand: AddCriteriaCommand,
  private val updateCriteriaCommand: UpdateCriteriaCommand,
  private val deleteCriteriaCommand: DeleteCriteriaCommand
) {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun addCriteria(
    @PathVariable("decisionId") decisionId: UUID,
    @Valid @RequestBody input: AddCriteriaInput
  ): CriteriaOutput =
    addCriteriaCommand.execute(decisionId, input)
      .let(::CriteriaOutput)

  @PatchMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.OK)
  fun updateCriteria(
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID,
    @RequestBody input: UpdateCriteriaInput
  ): CriteriaOutput =
    updateCriteriaCommand.execute(decisionId, criteriaId, input)
      .let(::CriteriaOutput)

  @DeleteMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteCriteria(
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID
  ): Unit =
    deleteCriteriaCommand.execute(decisionId, criteriaId)
}