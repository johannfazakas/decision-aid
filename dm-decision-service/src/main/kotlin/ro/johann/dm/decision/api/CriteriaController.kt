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
import ro.johann.dm.decision.transfer.AddCriteriaRequest
import ro.johann.dm.decision.transfer.CriteriaTO
import ro.johann.dm.decision.transfer.UpdateCriteriaRequest
import java.util.UUID

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
    @RequestBody request: AddCriteriaRequest
  ): CriteriaTO =
    addCriteriaCommand.execute(decisionId, request)
      .let(::CriteriaTO)

  @PatchMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.OK)
  fun updateCriteria(
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID,
    @RequestBody request: UpdateCriteriaRequest
  ): CriteriaTO =
    updateCriteriaCommand.execute(decisionId, criteriaId, request)
      .let(::CriteriaTO)

  @DeleteMapping("/{criteriaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteCriteria(
    @PathVariable("decisionId") decisionId: UUID,
    @PathVariable("criteriaId") criteriaId: UUID
  ): Unit =
    deleteCriteriaCommand.execute(decisionId, criteriaId)
}