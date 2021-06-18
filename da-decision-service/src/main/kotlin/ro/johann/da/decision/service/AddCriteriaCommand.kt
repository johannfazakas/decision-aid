package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.AddCriteriaInput
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class AddCriteriaCommand(
  private val decisionRepository: DecisionRepository,
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AddCriteriaCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, input: AddCriteriaInput): Criteria {
    logger.info("add criteria >> userId = $userId, decisionId = $decisionId, input = $input")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    return decision
      .let {
        val now = LocalDateTime.now()
        Criteria(
          name = input.name,
          weight = input.weight,
          unitOfMeasure = input.unitOfMeasure,
          type = input.type,
          decision = it,
          createdAt = now,
          updatedAt = now
        )
      }
      .let(criteriaRepository::save)
  }
}