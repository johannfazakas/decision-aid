package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.persistence.DecisionRepository
import java.util.UUID

@Service
class DeleteDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteDecisionCommand::class.java)
  }

  fun execute(id: UUID) {
    logger.info("delete decision >> id = $id")
    decisionRepository.deleteById(id)
  }
}