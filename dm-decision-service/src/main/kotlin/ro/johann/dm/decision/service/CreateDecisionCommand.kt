package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Decision
import ro.johann.dm.decision.persistence.DecisionRepository
import ro.johann.dm.decision.transfer.CreateDecisionInput

@Service
class CreateDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(CreateDecisionCommand::class.java)
  }

  fun execute(input: CreateDecisionInput): Decision {
    logger.info("create decision >> input = $input")
    return decisionRepository.save(
      Decision(
        name = input.name,
        description = input.description
      )
    )
  }
}