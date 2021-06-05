package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.UpdateCriteriaInput
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class UpdateCriteriaCommand(
  private val criteriaRepository: CriteriaRepository,
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateCriteriaCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, criteriaId: UUID, input: UpdateCriteriaInput): Criteria {
    logger.info("update criteria >> userId = $userId, decisionId = $decisionId, criteriaId = $criteriaId, input = $input")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    val criteria = criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?: throw Errors.criteriaNotFound(decisionId, criteriaId)

    return criteria
      .also(::validate)
      .apply {
        input.weight?.also { weight = it }
        input.name?.also { name = it }
        input.unitOfMeasure?.also { unitOfMeasure = it }
        input.type?.also { type = it }
        updatedAt = LocalDateTime.now()
      }
      .also(criteriaRepository::save)
  }

  private fun validate(criteria: Criteria) {
    if (criteria.decision.status === DecisionStatus.PROCESSED) {
      throw Errors.invalidDecisionStatus(decisionId = criteria.decision.id, DecisionStatus.PROCESSED)
    }
  }
}