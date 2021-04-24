package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.UpdateCriteriaInput
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class UpdateCriteriaCommand(
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateCriteriaCommand::class.java)
  }

  fun execute(decisionId: UUID, criteriaId: UUID, input: UpdateCriteriaInput): Criteria {
    logger.info("update criteria >> decisionId = $decisionId, criteriaId = $criteriaId, input = $input")

    return criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?.also { criteria ->
        input.weight?.also { criteria.weight = it }
        input.name?.also { criteria.name = it }
        input.unitOfMeasure?.also { criteria.unitOfMeasure = it }
        input.type?.also { criteria.type = it }
      }
      ?.also(criteriaRepository::save)
      ?: throw Errors.criteriaNotFound(decisionId, criteriaId)
  }
}