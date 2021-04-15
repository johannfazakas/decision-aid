package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.dm.decision.api.transfer.UpdateDecisionInput
import ro.johann.dm.decision.domain.Decision
import ro.johann.dm.decision.persistence.DecisionRepository
import ro.johann.dm.decision.service.error.Errors
import java.util.UUID

@Service
class UpdateDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateDecisionCommand::class.java)
  }

  fun execute(decisionId: UUID, input: UpdateDecisionInput): Decision {
    logger.info("update decision >> decisionId = $decisionId, input = $input")

    return decisionRepository.findByIdOrNull(decisionId)
      ?.also { decision ->
        input.name
          ?.takeIf { it.isNotEmpty() }
          ?.let { decision.name = it }
        input.description
          ?.takeIf { it.isNotEmpty() }
          ?.let { decision.description = it }
      }
      ?.also(decisionRepository::save)
      ?: throw Errors.decisionNotFound(decisionId)
  }
}
