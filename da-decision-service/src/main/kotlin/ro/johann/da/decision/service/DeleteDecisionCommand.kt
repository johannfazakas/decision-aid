package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class DeleteDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteDecisionCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID) {
    logger.info("delete decision >> userId = $userId, decisionId = $decisionId")

    decisionRepository.findByIdOrNull(decisionId)
      ?.takeIf { it.userId == userId }
      ?.also { decisionRepository.deleteById(decisionId) }
      ?: throw Errors.decisionNotFound(decisionId)
  }
}