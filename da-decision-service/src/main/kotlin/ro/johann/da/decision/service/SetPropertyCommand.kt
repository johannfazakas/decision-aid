package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.SetPropertyInput
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.Property
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.persistence.PropertyRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class SetPropertyCommand(
  private val propertyRepository: PropertyRepository,
  private val alternativeRepository: AlternativeRepository,
  private val criteriaRepository: CriteriaRepository,
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(SetPropertyCommand::class.java)
  }

  fun execute(userId: UUID, decisionId: UUID, input: SetPropertyInput): Property {
    logger.info("set property >> userId = $userId, decisionId = $decisionId, input = $input")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw Errors.decisionNotFound(decisionId)
    decision.takeIf { it.userId == userId }
      ?: throw Errors.notAuthorizedOnDecision(decisionId)

    val alternative = alternativeRepository.findByIdAndDecisionId(input.alternativeId, decisionId)
      ?: throw Errors.alternativeNotFound(decisionId, input.alternativeId)

    val property = alternative.properties
      .find { property -> input.criteriaId == property.criteria.id }
      ?.also { existingProperty ->
        existingProperty.value = input.value
        existingProperty.updatedAt = LocalDateTime.now()
      }
      ?: let {
        val criteria = criteriaRepository.findByIdAndDecisionId(input.criteriaId, decisionId)
          ?: throw Errors.criteriaNotFound(decisionId, input.criteriaId)
        val now = LocalDateTime.now()
        Property(
          value = input.value,
          alternative = alternative,
          criteria = criteria,
          createdAt = now,
          updatedAt = now
        )
      }
    return propertyRepository.save(property)
  }
}
