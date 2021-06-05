package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class DeleteAlternativeCommand(
  private val alternativeRepository: AlternativeRepository,
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteAlternativeCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, alternativeId: UUID) {
    logger.info("delete alternative >> userId = $userId, decisionId = $decisionId, alternativeId = $alternativeId")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    alternativeRepository.findByIdAndDecisionId(alternativeId, decisionId)
      ?.also { validate(it) }
      ?.also { alternativeRepository.deleteById(alternativeId) }
  }

  // TODO check if still required
  private fun validate(alternative: Alternative) {
    if (alternative.decision.status == DecisionStatus.PROCESSED) {
      throw Errors.invalidDecisionStatus(alternative.decision.id, DecisionStatus.PROCESSED)
    }
  }
}
