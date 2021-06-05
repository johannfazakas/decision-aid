package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.CreateDecisionInput
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.DecisionRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class CreateDecisionCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(CreateDecisionCommand::class.java)
  }

  fun execute(userId: UUID, input: CreateDecisionInput): Decision {
    logger.info("create decision >> userId = $userId input = $input")

    val now = LocalDateTime.now()
    return decisionRepository.save(
      Decision(
        name = input.name,
        description = input.description,
        status = DecisionStatus.DESIGN,
        createdAt = now,
        updatedAt = now
      )
    )
  }
}