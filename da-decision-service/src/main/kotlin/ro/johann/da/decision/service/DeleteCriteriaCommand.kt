package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class DeleteCriteriaCommand(
  private val criteriaRepository: CriteriaRepository,
  private val decisionRepository: DecisionRepository,
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteCriteriaCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, criteriaId: UUID) {
    logger.info("delete criteria >> userId = $userId, decisionId = $decisionId, criteriaId = $criteriaId")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?.also { validate(it) }
      ?.also { criteriaRepository.deleteById(criteriaId) }
  }

  private fun validate(criteria: Criteria) {
    if (criteria.decision.status === DecisionStatus.PROCESSED) {
      throw Errors.invalidDecisionStatus(criteria.decision.id, DecisionStatus.PROCESSED)
    }
  }
}