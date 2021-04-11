package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Criteria
import ro.johann.dm.decision.error.Errors
import ro.johann.dm.decision.persistence.CriteriaRepository
import ro.johann.dm.decision.transfer.UpdateCriteriaInput
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
      ?.also { criteria -> input.weight?.also { criteria.weight = it } }
      ?.also(criteriaRepository::save)
      ?: throw Errors.criteriaNotFound(decisionId, criteriaId)
  }
}