package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors.decisionNotFound
import java.util.UUID

@Service
class GetDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(GetDecisionCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID): Decision {
    logger.info("get decision >> userId = $userId, decisionId = $decisionId")

    return decisionRepository.findByIdOrNull(decisionId)
      ?.takeIf { it.userId == userId }
      ?: throw decisionNotFound(decisionId)
  }
}