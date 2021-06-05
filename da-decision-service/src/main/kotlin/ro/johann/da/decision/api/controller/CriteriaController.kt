package ro.johann.da.decision.api.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ro.johann.da.decision.api.transfer.AddCriteriaInput
import ro.johann.da.decision.api.transfer.CriteriaOutput
import ro.johann.da.decision.api.transfer.UpdateCriteriaInput
import ro.johann.da.decision.service.AddCriteriaCommand
import ro.johann.da.decision.service.DeleteCriteriaCommand
import ro.johann.da.decision.service.UpdateCriteriaCommand
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
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @Valid @RequestBody input: AddCriteriaInput
  ): CriteriaOutput =
    addCriteriaCommand.execute(userId, decisionId, input)
      .let(::CriteriaOutput)

  @PatchMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.OK)
  fun updateCriteria(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID,
    @Valid @RequestBody input: UpdateCriteriaInput
  ): CriteriaOutput =
    updateCriteriaCommand.execute(userId, decisionId, criteriaId, input)
      .let(::CriteriaOutput)

  @DeleteMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteCriteria(
    @RequestHeader(Headers.USER_ID) userId: UUID,
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID
  ): Unit =
    deleteCriteriaCommand.execute(userId, decisionId, criteriaId)
}