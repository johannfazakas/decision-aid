package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.AddAlternativeInput
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class AddAlternativeCommand(
  private val decisionRepository: DecisionRepository,
  private val alternativeRepository: AlternativeRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AddAlternativeCommand::class.java)
  }

  fun execute(decisionId: UUID, input: AddAlternativeInput): Alternative {
    logger.info("add alternative >> decisionId = $decisionId, input = $input")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)

    return decision
      .also { validate(it) }
      .let {
        val now = LocalDateTime.now()
        Alternative(
          name = input.name,
          decision = it,
          createdAt = now,
          updatedAt = now
        )
      }
      .let { alternative -> alternativeRepository.save(alternative) }
  }

  private fun validate(decision: Decision) {
    when {
      decision.status == DecisionStatus.PROCESSED -> throw Errors.invalidDecisionStatus(decision.id)
    }
  }
}
