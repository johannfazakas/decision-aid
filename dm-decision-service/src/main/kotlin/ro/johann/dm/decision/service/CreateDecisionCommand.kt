package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Decision
import ro.johann.dm.decision.persistence.DecisionRepository
import ro.johann.dm.decision.transfer.CreateDecisionRequest

@Service
class CreateDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(CreateDecisionCommand::class.java)
  }

  fun execute(request: CreateDecisionRequest): Decision {
    logger.info("create decision >> request = $request")
    return decisionRepository.save(
      Decision(
        name = request.name,
        description = request.description
      )
    )
  }
}