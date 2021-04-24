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

  fun execute(id: UUID) {
    logger.info("delete decision >> id = $id")
    decisionRepository.findByIdOrNull(id)
      ?.also { decisionRepository.deleteById(id) }
      ?: throw Errors.decisionNotFound(id)
  }
}