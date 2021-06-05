package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.persistence.DecisionRepository
import java.util.UUID

@Service
class ListDecisionsCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(ListDecisionsCommand::class.java)
  }

  fun execute(userId: UUID): Iterable<Decision> {
    logger.info("list decisions >> userId = $userId")

    return decisionRepository.findByUserId(userId).sortedBy { it.createdAt }
  }
}