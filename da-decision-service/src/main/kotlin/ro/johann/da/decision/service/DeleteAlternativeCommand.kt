package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class DeleteAlternativeCommand(
  private val alternativeRepository: AlternativeRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteAlternativeCommand::class.java)
  }

  fun execute(decisionId: UUID, alternativeId: UUID) {
    logger.info("delete alternative >> decisionId = $decisionId, alternativeId = $alternativeId")

    alternativeRepository.findByIdAndDecisionId(alternativeId, decisionId)
      ?.also { validate(it) }
      ?.also { alternativeRepository.deleteById(alternativeId) }
  }

  private fun validate(alternative: Alternative) {
    if (alternative.decision.status == DecisionStatus.PROCESSED) {
      throw Errors.invalidDecisionStatus(alternative.decision.id, DecisionStatus.PROCESSED)
    }
  }
}
