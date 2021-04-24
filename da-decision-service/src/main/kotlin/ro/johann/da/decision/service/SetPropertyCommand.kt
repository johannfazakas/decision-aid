package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.SetPropertyInput
import ro.johann.da.decision.domain.Property
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.PropertyRepository
import ro.johann.da.decision.service.error.Errors
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

  fun execute(decisionId: UUID, input: SetPropertyInput): Property {
    logger.info("set property >> decisionId = $decisionId, input = $input")

    val alternative = alternativeRepository.findByIdAndDecisionId(input.alternativeId, decisionId)
      ?: throw Errors.alternativeNotFound(decisionId, input.alternativeId)
    val property = alternative.properties
      .find { property -> input.criteriaId == property.criteria.id }
      ?.also { it.value = input.value }
      ?: let {
        val criteria = criteriaRepository.findByIdAndDecisionId(input.criteriaId, decisionId)
          ?: throw Errors.criteriaNotFound(decisionId, input.criteriaId)
        Property(
          value = input.value,
          alternative = alternative,
          criteria = criteria
        )
      }
    return propertyRepository.save(property)
  }
}
