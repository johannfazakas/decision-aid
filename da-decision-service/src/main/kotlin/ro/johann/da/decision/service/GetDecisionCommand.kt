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

  fun execute(id: UUID): Decision {
    logger.info("get decision >> id = $id")

    return decisionRepository.findByIdOrNull(id)
      ?: throw decisionNotFound(id)
  }
}