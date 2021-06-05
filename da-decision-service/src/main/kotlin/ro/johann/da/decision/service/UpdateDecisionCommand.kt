package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.UpdateDecisionInput
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class UpdateDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateDecisionCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, input: UpdateDecisionInput): Decision {
    logger.info("update decision >> userId = $userId, decisionId = $decisionId, input = $input")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    return decision
      .apply {
        input.name
          ?.takeIf { it.isNotBlank() }
          ?.let { name = it }
        input.description
          ?.takeIf { it.isNotBlank() }
          ?.let { description = it }
        updatedAt = LocalDateTime.now()
      }
      .also(decisionRepository::save)
  }
}
