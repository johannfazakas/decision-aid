package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.UpdateCriteriaInput
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
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

    val criteria = criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?: throw Errors.criteriaNotFound(decisionId, criteriaId)

    return criteria
      .apply {
        input.weight?.also { weight = it }
        input.name?.also { name = it }
        input.unitOfMeasure?.also { unitOfMeasure = it }
        input.type?.also { type = it }
        updatedAt = LocalDateTime.now()
      }
      .also(criteriaRepository::save)
  }
}