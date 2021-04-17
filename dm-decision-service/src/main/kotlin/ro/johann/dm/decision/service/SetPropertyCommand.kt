package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.api.transfer.SetPropertyInput
import ro.johann.dm.decision.domain.Property
import ro.johann.dm.decision.persistence.AlternativeRepository
import ro.johann.dm.decision.persistence.CriteriaRepository
import ro.johann.dm.decision.persistence.PropertyRepository
import ro.johann.dm.decision.service.error.Errors
import java.util.UUID

@Service
class SetPropertyCommand(
  private val propertyRepository: PropertyRepository,
  private val alternativeRepository: AlternativeRepository,
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(SetPropertyCommand::class.java)
  }

  fun execute(decisionId: UUID, alternativeId: UUID, criteriaId: UUID, input: SetPropertyInput) {
    logger.info("set property >> decisionId = $decisionId, alternativeId = $alternativeId, criteriaId = $criteriaId, input = $input")

    val alternative = alternativeRepository.findByIdAndDecisionId(alternativeId, decisionId)
      ?: throw Errors.alternativeNotFound(decisionId, alternativeId)
    val property = alternative.properties
      .find { property -> criteriaId == property.criteria.id }
      ?.also { it.value = input.value }
      ?: let {
        val criteria = criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
          ?: throw Errors.criteriaNotFound(decisionId, criteriaId)
        Property(
          value = input.value,
          alternative = alternative,
          criteria = criteria
        )
      }
    propertyRepository.save(property)
  }
}
