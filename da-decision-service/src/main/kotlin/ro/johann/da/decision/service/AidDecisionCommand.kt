package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class AidDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AidDecisionCommand::class.java)
  }

  fun execute(id: UUID): Decision {
    logger.info("aid decision >> decisionId = $id")

    return decisionRepository.findByIdOrNull(id)
      ?.also(::validateAid)
      ?.apply {
        status = DecisionStatus.PROCESSED
        updatedAt = LocalDateTime.now()
      }
      ?.let(decisionRepository::save)
      ?: throw Errors.decisionNotFound(id)
  }

  private fun validateAid(decision: Decision) {
    if (decision.criteria.isEmpty()) {
      throw Errors.noCriteriaDefined(decision.id)
    }
  }
}
